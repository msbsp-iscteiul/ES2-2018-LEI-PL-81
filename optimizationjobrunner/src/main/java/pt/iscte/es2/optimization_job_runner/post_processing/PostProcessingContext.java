package pt.iscte.es2.optimization_job_runner.post_processing;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import pt.iscte.es2.optimization_job_runner.jobs.Job;

import java.util.List;

/**
 * Context in which a solution was created
 */
public class PostProcessingContext {
	private final String experimentName;
	private final String solutionsPath;
	private final String experimentPath;
	private final List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms;
	private final Job job;

	/**
	 * Constructor
	 * @param experimentName experiment name
	 * @param solutionsPath solutions path
	 * @param experimentPath experiment path
	 * @param algorithms chosen algorithms
	 * @param job the job id
	 */
	public PostProcessingContext(
		String experimentName, String solutionsPath, String experimentPath,
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms, Job job
	) {
		this.experimentName = experimentName;
		this.solutionsPath = solutionsPath;
		this.experimentPath = experimentPath;
		this.algorithms = algorithms;
		this.job = job;
	}

	/**
	 * @return experiment name
	 */
	public String getExperimentName() {
		return experimentName;
	}

	/**
	 * @return solutions path
	 */
	public String getSolutionsPath() {
		return solutionsPath;
	}

	/**
	 * @return experiment path
	 */
	public String getExperimentPath() {
		return experimentPath;
	}

	/**
	 * @return the chosen algorithms
	 */
	public List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> getAlgorithms() {
		return algorithms;
	}

	/**
	 * @return the job id
	 */
	public Job getJob() {
		return job;
	}
}
