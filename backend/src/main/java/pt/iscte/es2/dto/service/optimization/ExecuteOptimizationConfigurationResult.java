package pt.iscte.es2.dto.service.optimization;

/**
 * DTO ExecuteOptimizationConfiguration Result
 *
 * Result that contains an id, {@link Integer}, of the started execution of an OptimizationConfiguration. Also contains a
 * message, {@link String}, to control the Success/Failure of the application
 */
public class ExecuteOptimizationConfigurationResult {

	private Integer id;
	private String message;

	public ExecuteOptimizationConfigurationResult() {

	}

	public ExecuteOptimizationConfigurationResult(Integer id, String message) {
		this.id = id;
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
