package pt.iscte.es2.optimization_job_runner.jmetal.util.experiment;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentComponent;

import java.io.File;

public class OptimizationJobRunnerExecuteAlgorithms<S extends Solution<?>, Result> implements ExperimentComponent {
	private Experiment<S, Result> experiment;

	/**
	 * Constructor
	 */
	public OptimizationJobRunnerExecuteAlgorithms(Experiment<S, Result> configuration) {
		this.experiment = configuration;
	}

	@Override
	public void run() {
		JMetalLogger.logger.info("ExecuteAlgorithms: Preparing output directory");
		prepareOutputDirectory();

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
			"" + this.experiment.getNumberOfCores());

		final Thread parentThread = Thread.currentThread();
		for (int i = 0; i < experiment.getIndependentRuns(); i++) {
			final int id = i;
			if (!parentThread.isInterrupted()) {
				experiment.getAlgorithmList()
					.parallelStream()
					.forEach(algorithm -> {
						if (!parentThread.isInterrupted()) {
							algorithm.runAlgorithm(id, experiment);
						}
					});
			}
		}
	}


	private void prepareOutputDirectory() {
		if (experimentDirectoryDoesNotExist()) {
			createExperimentDirectory();
		}
	}

	private boolean experimentDirectoryDoesNotExist() {
		boolean result;
		File experimentDirectory;

		experimentDirectory = new File(experiment.getExperimentBaseDirectory());
		if (experimentDirectory.exists() && experimentDirectory.isDirectory()) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	private void createExperimentDirectory() {
		File experimentDirectory;
		experimentDirectory = new File(experiment.getExperimentBaseDirectory());

		if (experimentDirectory.exists()) {
			experimentDirectory.delete();
		}

		boolean result;
		result = new File(experiment.getExperimentBaseDirectory()).mkdirs();
		if (!result) {
			throw new JMetalException("Error creating experiment directory: " +
				experiment.getExperimentBaseDirectory());
		}
	}
}
