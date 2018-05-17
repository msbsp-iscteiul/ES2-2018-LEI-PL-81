package pt.iscte.es2.optimization_job_runner.post_processing;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;

import java.util.List;

public class PostProcessingContext {
	private final String experimentName;
	private final String problemName;
	private final String solutionsPath;
	private final String experimentPath;
	private final List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms;
	private final Long jobId;

	public PostProcessingContext(
		String experimentName, String problemName, String solutionsPath, String experimentPath,
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms, Long jobId
	) {
		this.experimentName = experimentName;
		this.problemName = problemName;
		this.solutionsPath = solutionsPath;
		this.experimentPath = experimentPath;
		this.algorithms = algorithms;
		this.jobId = jobId;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public String getProblemName() {
		return problemName;
	}

	public String getSolutionsPath() {
		return solutionsPath;
	}

	public String getExperimentPath() {
		return experimentPath;
	}

	public List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> getAlgorithms() {
		return algorithms;
	}

	public Long getJobId() {
		return jobId;
	}
}
