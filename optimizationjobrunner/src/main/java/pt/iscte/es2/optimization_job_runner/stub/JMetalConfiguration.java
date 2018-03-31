package pt.iscte.es2.optimization_job_runner.stub;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.SecureClientClassLoader;
import pt.iscte.es2.client_jar_loader.SecurityPolicy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Policy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class JMetalConfiguration {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, InvocationTargetException {
//		setSecurityContext();
		Problem<Solution<?>> clientOptimizationProblem = getClientOptimizationProblem();
		List<Constructor<?>> algorithmFactoryConstructors =
			new AlgorithmFinder(clientOptimizationProblem).execute();
		ExperimentProblem<Solution<?>> problem = new ExperimentProblem<>(clientOptimizationProblem);
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms = getExperimentAlgorithms(
			problem,
			algorithmFactoryConstructors
		);

		Experiment<Solution<?>, List<Solution<?>>> experiment =
			new ExperimentBuilder("Experiment")
				.setProblemList(Collections.singletonList(problem))
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
				new ExperimentAlgorithm<>(algorithm, algorithm.getName(), experimentProblem.getTag())
			);
		}
		return algorithms;
	}

	private static Problem<Solution<?>> getClientOptimizationProblem()
		throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		File jar = new File(SecureClientClassLoader.class.getClassLoader().getResource("data/containee-1.0-SNAPSHOT.jar").getFile());
		String file = "file://" + jar.getAbsolutePath();
		ClassLoader clientClassLoader = new SecureClientClassLoader(new URL(file));
		Class<?> pluginClass = clientClassLoader.loadClass("Plugin");
		Problem plugin = (Problem) pluginClass.newInstance();
		return plugin;
	}

	private static void setSecurityContext() {
		Policy.setPolicy(new SecurityPolicy());
		System.setSecurityManager(new SecurityManager());
	}
}
