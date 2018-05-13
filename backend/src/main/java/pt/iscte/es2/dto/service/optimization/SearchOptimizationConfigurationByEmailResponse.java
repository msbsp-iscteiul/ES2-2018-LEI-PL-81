package pt.iscte.es2.dto.service.optimization;

public class SearchOptimizationConfigurationByEmailResponse {

	private SummaryOptimizationConfigurationResult result;

	public SearchOptimizationConfigurationByEmailResponse(SummaryOptimizationConfigurationResult result) {
		this.result = result;
	}

	public SummaryOptimizationConfigurationResult getResult() {
		return result;
	}

	public void setResult(SummaryOptimizationConfigurationResult result) {
		this.result = result;
	}
}
