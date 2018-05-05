package pt.iscte.es2.dto;

/**
 * DTO of the OptimizationConfigurationUserSolutions
 */
public class OptimizationConfigurationUserSolutions {

	private Integer id;
	private String solutionQuality;

	public OptimizationConfigurationUserSolutions() {

	}

	public OptimizationConfigurationUserSolutions(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSolutionQuality() {
		return solutionQuality;
	}

	public void setSolutionQuality(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}
}
