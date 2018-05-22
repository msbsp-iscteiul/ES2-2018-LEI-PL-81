package pt.iscte.es2.dto.service.optimization;

/**
 * DTO Save OptimizationConfiguration Response
 *
 * Contains the save result {@link SaveOptimizationConfigurationResult} of the submitted problem
 */
public class SaveOptimizationConfigurationResponse {

	private SaveOptimizationConfigurationResult result;

	public SaveOptimizationConfigurationResponse() {

	}

	public SaveOptimizationConfigurationResponse(SaveOptimizationConfigurationResult result) {
		this.result = result;
	}

	public SaveOptimizationConfigurationResult getResult() {
		return result;
	}

	public void setResult(SaveOptimizationConfigurationResult result) {
		this.result = result;
	}
}
