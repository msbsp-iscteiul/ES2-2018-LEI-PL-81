package pt.iscte.es2.optimization_job_runner;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.client_jar_loader.SecurityPolicy;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.EvaluationsCounter;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.ObservableProblem;
import pt.iscte.es2.optimization_job_runner.jmetal.problem.observable.ObservableProblemFactory;
import pt.iscte.es2.optimization_job_runner.mail.MailSenderProvider;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.Policy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
@Service
public class JMetalConfiguration {

	private static final Logger LOGGER = Logger.getLogger(JMetalConfiguration.class.getName());
	private static final int INDEPENDENT_RUNS = 1;

	// @PostConstruct
	public void run(JavaMailSender mailSender) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, InvocationTargetException {
//		setSecurityContext();
		Problem<Solution<?>> clientOptimizationProblem = new LoadClientJarProblem()
			.loadProblemFromJar("optimizationjobrunner/target/data/containee-1.0-SNAPSHOT-integer.jar");

		// Find the compatible algorithms
		AlgorithmFinder.AlgorithmFinderResult finderResult =
			new AlgorithmFinder(clientOptimizationProblem).execute();
		ObservableProblem<Solution<?>> observableProblem = new ObservableProblemFactory()
			.createFromProblem(clientOptimizationProblem);
		ExperimentProblem<Solution<?>> experimentProblem = new ExperimentProblem(observableProblem);
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms = getExperimentAlgorithms(
			experimentProblem,
			finderResult.getConstructors()
		);

		// Setup evaluations counter
		EvaluationsCounter evaluationsCounter = new EvaluationsCounter(
			mailSender,
			algorithms.size() * AlgorithmConstants.MAX_EVALUTIONS);
		observableProblem.registerEvaluationListener(evaluationsCounter);

		// Experiment setup
		Experiment<Solution<?>, List<Solution<?>>> experiment =
			new ExperimentBuilder("Experiment")
				.setIndependentRuns(INDEPENDENT_RUNS)
				.setProblemList(Collections.singletonList(experimentProblem))
				.setAlgorithmList(algorithms)
				.setNumberOfCores(Math.max(Runtime.getRuntime().availableProcessors() / 2, 1))
				.setExperimentBaseDirectory("experimentBaseDirectory")
				.setReferenceFrontDirectory("experimentBaseDirectory/referenceFronts")
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setIndicatorList(Collections.singletonList(new PISAHypervolume<>()))
				.build();
		new ExecuteAlgorithms<>(experiment).run();
		new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		new ComputeQualityIndicators<>(experiment).run();
		new GenerateLatexTablesWithStatistics(experiment).run();
		new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
		LOGGER.log(Level.INFO, String.valueOf(evaluationsCounter.getCount()));
	}

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

	private static void setSecurityContext() {
		Policy.setPolicy(new SecurityPolicy());
		System.setSecurityManager(new SecurityManager());
	}
}
