package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.SummaryOptimizationConfiguration;

import java.util.ArrayList;
import java.util.List;

public class SummaryOptimizationConfigurationResult {

	private List<SummaryOptimizationConfiguration> summary;

	public SummaryOptimizationConfigurationResult() {

	}

	public SummaryOptimizationConfigurationResult(List<SummaryOptimizationConfiguration> summary) {
		this.summary = summary;
	}

	public List<SummaryOptimizationConfiguration> getSummary() {
		if (summary == null) {
			summary = new ArrayList<>();
		}
		return summary;
	}

	public void setSummary(List<SummaryOptimizationConfiguration> summary) {
		this.summary = summary;
	}
}
