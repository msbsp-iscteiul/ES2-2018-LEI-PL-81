package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;

/**
 * DTO OptimizationConfiguration Request
 *
 * Contains an email of the User and the ID of the OptimizationConfiguration with the purpose of searching
 * for an {@link OptimizationConfiguration}
 */
public class SearchOptimizationConfigurationByIdAndEmailRequest {

	private Integer id;
	private String email;

	public SearchOptimizationConfigurationByIdAndEmailRequest() {

	}

	public SearchOptimizationConfigurationByIdAndEmailRequest(Integer id, String email) {
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
