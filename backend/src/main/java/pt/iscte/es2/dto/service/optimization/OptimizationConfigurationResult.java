package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;

public class OptimizationConfigurationResult {

	private OptimizationConfiguration optimizationConfiguration;

	public OptimizationConfigurationResult() {

	}

	public OptimizationConfigurationResult(OptimizationConfiguration optimizationConfiguration) {
		this.optimizationConfiguration = optimizationConfiguration;
	}

	public OptimizationConfiguration getOptimizationConfiguration() {
		return optimizationConfiguration;
	}

	public void setOptimizationConfiguration(OptimizationConfiguration optimizationConfiguration) {
		this.optimizationConfiguration = optimizationConfiguration;
	}
}
