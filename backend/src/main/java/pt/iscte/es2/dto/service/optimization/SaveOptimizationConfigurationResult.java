package pt.iscte.es2.dto.service.optimization;

/**
 * DTO Save OptimizationConfiguration Result
 *
 * Result of the saved OptimizationConfiguration that contains the id of the persisted OptimizationConfiguration
 * and a message in case of Success or Failure
 */
public class SaveOptimizationConfigurationResult {

	private Integer id;
	private String message;

	public SaveOptimizationConfigurationResult() {

	}

	public SaveOptimizationConfigurationResult(Integer id) {
		this.id = id;
	}

	public SaveOptimizationConfigurationResult(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
