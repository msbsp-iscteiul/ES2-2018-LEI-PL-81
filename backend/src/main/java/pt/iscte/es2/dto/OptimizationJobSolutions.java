package pt.iscte.es2.dto;

/**
 * DTO for the Optimization Job Solutions
 */
public class OptimizationJobSolutions {

	private Integer id;
	private OptimizationJobExecutions optimizationJobExecutions;
	private String algorithmName;
	private String solution;
	private String solutionQuality;
	private String quality;

	public OptimizationJobSolutions() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OptimizationJobExecutions getOptimizationJobExecutions() {
		return optimizationJobExecutions;
	}

	public void setOptimizationJobExecutions(OptimizationJobExecutions optimizationJobExecutions) {
		this.optimizationJobExecutions = optimizationJobExecutions;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getSolutionQuality() {
		return solutionQuality;
	}

	public void setSolutionQuality(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
}
