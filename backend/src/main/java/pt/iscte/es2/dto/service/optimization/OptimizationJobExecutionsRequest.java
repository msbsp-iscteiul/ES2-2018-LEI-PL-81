package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationJobExecutions Request
 *
 * Request that contains an email of the User to search the OptimizationJobExecutions associated with it.
 */
public class OptimizationJobExecutionsRequest {

	private String email;

	public OptimizationJobExecutionsRequest() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
