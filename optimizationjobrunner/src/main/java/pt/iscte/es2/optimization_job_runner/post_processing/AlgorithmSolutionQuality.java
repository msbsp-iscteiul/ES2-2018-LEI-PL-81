package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.jmetal.solution.SolutionQuality;

import java.io.Serializable;

public class AlgorithmSolutionQuality implements Serializable {
	private final String algorithmName;
	private final String solution;
	private final SolutionQuality solutionQuality;

	public AlgorithmSolutionQuality(String algorithmName, String solution, SolutionQuality solutionQuality) {
		this.algorithmName = algorithmName;
		this.solution = solution;
		this.solutionQuality = solutionQuality;
	}

	public double quality() {
		return solutionQuality.quality();
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public String getSolution() {
		return solution;
	}

	public SolutionQuality getSolutionQuality() {
		return solutionQuality;
	}

}
