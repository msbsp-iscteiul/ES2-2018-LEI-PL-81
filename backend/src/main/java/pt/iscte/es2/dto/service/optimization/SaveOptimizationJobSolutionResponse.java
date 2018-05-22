package pt.iscte.es2.dto.service.optimization;

public class SaveOptimizationJobSolutionResponse {

	private SaveOptimizationJobSolutionResult result;

	public SaveOptimizationJobSolutionResponse() {

	}

	public SaveOptimizationJobSolutionResponse(SaveOptimizationJobSolutionResult result) {

	}

	public SaveOptimizationJobSolutionResult getResult() {
		return result;
	}

	public void setResult(SaveOptimizationJobSolutionResult result) {
		this.result = result;
	}
}
