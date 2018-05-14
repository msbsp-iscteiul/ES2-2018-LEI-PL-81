package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.jmetal.solution.SolutionQuality;

import java.util.List;

public class AlgorithmSolutionQuality {
	private final String algorithmName;
	private final List<Double> solution;
	private final SolutionQuality solutionQuality;

	public AlgorithmSolutionQuality(String algorithmName, List<Double> solution, SolutionQuality solutionQuality) {
		this.algorithmName = algorithmName;
		this.solution = solution;
		this.solutionQuality = solutionQuality;
	}

	public double quality() {
		return solutionQuality.quality();
	}
}
