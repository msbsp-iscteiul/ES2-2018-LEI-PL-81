package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;

/**
 * DTO execute OptimizationConfiguration Request
 *
 * Request that contains the id of an {@link OptimizationConfiguration} and associated email
 * of the User to start an Execution
 */
public class ExecuteOptimizationConfigurationRequest {

	private Integer id;
	private String email;

	public ExecuteOptimizationConfigurationRequest() {

	}

	public ExecuteOptimizationConfigurationRequest(Integer id, String email) {
		this.id = id;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
