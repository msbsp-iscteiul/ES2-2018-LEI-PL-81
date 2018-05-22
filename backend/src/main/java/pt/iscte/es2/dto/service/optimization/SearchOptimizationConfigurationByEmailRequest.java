package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationConfiguration Request
 *
 * Contains the email of the User to search for OptimizationConfiguration's associated with the email
 */
public class SearchOptimizationConfigurationByEmailRequest {

	private String email;

	public SearchOptimizationConfigurationByEmailRequest() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
