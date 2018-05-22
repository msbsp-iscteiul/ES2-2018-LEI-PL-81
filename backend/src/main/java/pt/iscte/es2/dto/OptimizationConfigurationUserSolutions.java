package pt.iscte.es2.dto;

/**
 * DTO of OptimizationConfigurationUserSolutions
 *
 * UserSolutions DTO that contains the solution quality
 */
public class OptimizationConfigurationUserSolutions {

	private String solutionQuality;

	public OptimizationConfigurationUserSolutions() {

	}

	public String getSolutionQuality() {
		return solutionQuality;
	}

	public void setSolutionQuality(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}
}
