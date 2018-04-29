package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationConfiguration Result
 */
public class OptimizationResult {

	private String message;

	public OptimizationResult() {

	}

	public OptimizationResult(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
