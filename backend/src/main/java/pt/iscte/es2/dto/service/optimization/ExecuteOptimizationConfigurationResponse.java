package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;
/**
 * DTO execute OptimizationConfiguration Response
 *
 * Response contains an {@link ExecuteOptimizationConfigurationResult} that results from an Execution
 * of an {@link OptimizationConfiguration}
 */
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
