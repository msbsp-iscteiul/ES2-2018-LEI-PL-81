package pt.iscte.es2.dto.service.optimization;

/**
 * DTO OptimizationJobExecutions Response
 *
 * Response that contains an {@link OptimizationJobExecutionsResult}
 */
public class OptimizationJobExecutionsResponse {

	private OptimizationJobExecutionsResult result;

	public OptimizationJobExecutionsResponse() {

	}

	public OptimizationJobExecutionsResponse(OptimizationJobExecutionsResult result) {
		this.result = result;
	}

	public OptimizationJobExecutionsResult getResult() {
		return result;
	}

	public void setResult(OptimizationJobExecutionsResult result) {
		this.result = result;
	}
}
