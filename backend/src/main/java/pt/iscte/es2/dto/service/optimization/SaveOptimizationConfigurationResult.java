package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationConfiguration Result
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
