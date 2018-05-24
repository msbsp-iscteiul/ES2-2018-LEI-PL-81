package pt.iscte.es2.optimization_job_runner;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.ExperimentComponent;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.ObservableProblem;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.ObservableProblemFactory;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.ProgressNotifier;
import pt.iscte.es2.optimization_job_runner.jmetal.util.experiment.OptimizationJobRunnerExecuteAlgorithms;
import pt.iscte.es2.optimization_job_runner.jobs.Job;
import pt.iscte.es2.optimization_job_runner.mail.MailSender;
import pt.iscte.es2.optimization_job_runner.post_processing.OptimizationJobResult;
import pt.iscte.es2.optimization_job_runner.post_processing.PostProcessingContext;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * The JMetal task
 */
@SuppressWarnings("unchecked")
public class JMetalTask implements Callable<OptimizationJobResult> {

	private static final Logger LOGGER = Logger.getLogger(JMetalTask.class.getName());
	private static final int INDEPENDENT_RUNS = 1;
	private final MailSender mailSender;
	private final Job job;
	private final String experimentName;
	private final String experimentBaseDirectory;
	private final String referenceFront;

	/**
	 * Constructor
	 * @param mailSender mail sender
	 * @param job job to execute
	 * @param experimentName experiment name
	 * @param experimentBaseDirectory experiment base directory
	 * @param referenceFront the reference front path
	 */
	public JMetalTask(
		MailSender mailSender, Job job,
		String experimentName, String experimentBaseDirectory, String referenceFront
	) {
		this.mailSender = mailSender;
		this.job = job;
		this.experimentName = experimentName;
		this.experimentBaseDirectory = experimentBaseDirectory;
		this.referenceFront = referenceFront;
	}

	/**
	 * Run the task
	 * @return the job result
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Override
	public OptimizationJobResult call() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
		final Problem<Solution<?>> clientOptimizationProblem = new LoadClientJarProblem()
			.loadProblemFromJar(job.getJarPath());

		// Find the compatible algorithms
		final AlgorithmFinder.AlgorithmFinderResult finderResult =
			new AlgorithmFinder(clientOptimizationProblem).execute();
		final ObservableProblem<Solution<?>> observableProblem = new ObservableProblemFactory()
			.createFromProblem(clientOptimizationProblem);
		final ExperimentProblem<Solution<?>> experimentProblem = new ExperimentProblem(observableProblem);
		final List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms = getExperimentAlgorithms(
			experimentProblem,
			finderResult.getConstructorsForAlgorithms(job.getAlgorithms())
		);
		// Setup evaluations counter
		final int totalEvaluations = INDEPENDENT_RUNS * algorithms.size() * AlgorithmConstants.MAX_EVALUTIONS;
		final ProgressNotifier progressNotifier = new ProgressNotifier(
			job,
			mailSender,
			totalEvaluations);
		observableProblem.registerEvaluationListener(progressNotifier);

		// Experiment setup
		final Experiment<Solution<?>, List<Solution<?>>> experiment =
			new ExperimentBuilder(experimentName)
				.setIndependentRuns(INDEPENDENT_RUNS)
				.setProblemList(Collections.singletonList(experimentProblem))
				.setAlgorithmList(algorithms)
				.setNumberOfCores(Math.max(Runtime.getRuntime().availableProcessors() / 2, 1))
				.setExperimentBaseDirectory(experimentBaseDirectory)
				.setReferenceFrontDirectory(referenceFront)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setIndicatorList(Collections.singletonList(new Spread<>()))
				.build();

		final List<ExperimentComponent> components = Arrays.asList(
			new OptimizationJobRunnerExecuteAlgorithms<>(experiment),
			new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment),
			new ComputeQualityIndicators<>(experiment),
			new GenerateLatexTablesWithStatistics(experiment),
			new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1)
		);
		for (ExperimentComponent component : components) {
			if (!Thread.currentThread().isInterrupted()) {
				component.run();
			}
		}

		LOGGER.info("Counted evaluations: " + String.valueOf(progressNotifier.getCount())
			+ "; Estimated total evaluations: " + totalEvaluations);

		return new OptimizationJobResult(
			new PostProcessingContext(
				experimentName,
				referenceFront,
				experimentBaseDirectory + "/" + experimentName,
				algorithms,
				job
			)
		);
	}

	/**
	 * Finds the experiment algorithms for the experiment
	 * @param experimentProblem the experiment
	 * @param algorithmFactoryConstructors the algorithm constructors
	 * @return the list of algorithms
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> getExperimentAlgorithms(
		ExperimentProblem<Solution<?>> experimentProblem,
		List<Constructor<?>> algorithmFactoryConstructors
	) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms = new ArrayList<>(
			algorithmFactoryConstructors.size());
		for (Constructor<?> constructor : algorithmFactoryConstructors) {
			AlgorithmBuilder clazz = (AlgorithmBuilder) constructor
				.newInstance(experimentProblem.getProblem());
			Algorithm algorithm = clazz.build();
			algorithms.add(
				new ExperimentAlgorithm<>(
					algorithm,
					algorithm.getName(),
					experimentProblem.getTag()
				)
			);
		}
		return algorithms;
	}
}
