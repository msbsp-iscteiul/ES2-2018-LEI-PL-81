package pt.iscte.es2.dto.service.optimization;

public class ExecuteOptimizationConfigurationResponse {

	private ExecuteOptimizationConfigurationResult result;

	public ExecuteOptimizationConfigurationResponse() {

	}

	public ExecuteOptimizationConfigurationResponse(ExecuteOptimizationConfigurationResult result) {
		this.result = result;
	}

	public ExecuteOptimizationConfigurationResult getResult() {
		return result;
	}

	public void setResult(ExecuteOptimizationConfigurationResult result) {
		this.result = result;
	}
}
