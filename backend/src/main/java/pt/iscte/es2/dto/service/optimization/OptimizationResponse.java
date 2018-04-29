package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationConfiguration Response
 */
public class OptimizationResponse {

	private OptimizationResult result;

	public OptimizationResponse() {

	}

	public OptimizationResponse(OptimizationResult result) {
		this.result = result;
	}

	public OptimizationResult getResult() {
		return result;
	}

	public void setResult(OptimizationResult result) {
		this.result = result;
	}
}
