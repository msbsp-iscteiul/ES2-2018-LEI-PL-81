package pt.iscte.es2.dto;

/**
 * DTO for the Optimization Job Solutions
 */
public class OptimizationJobSolutions {

	private OptimizationJobExecutions optimizationJobExecution;
	private String algorithmName;
	private String solutionName;
	private String solutionQuality;

	public OptimizationJobExecutions getOptimizationJobExecution() {
		return optimizationJobExecution;
	}

	public void setOptimizationJobExecution(OptimizationJobExecutions optimizationJobExecution) {
		this.optimizationJobExecution = optimizationJobExecution;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getSolutionQuality() {
		return solutionQuality;
	}

	public void setSolutionQuality(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}
}
