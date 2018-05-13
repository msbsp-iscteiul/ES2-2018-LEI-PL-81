package pt.iscte.es2.dto.service.optimization;

public class SearchOptimizationConfigurationByIdAndEmailResponse {

	private OptimizationConfigurationResult result;

	public SearchOptimizationConfigurationByIdAndEmailResponse() {

	}

	public SearchOptimizationConfigurationByIdAndEmailResponse(OptimizationConfigurationResult result) {
		this.result = result;
	}

	public OptimizationConfigurationResult getResult() {
		return result;
	}

	public void setResult(OptimizationConfigurationResult result) {
		this.result = result;
	}
}
