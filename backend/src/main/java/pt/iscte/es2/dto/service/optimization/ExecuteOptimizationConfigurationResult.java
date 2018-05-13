package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationJobExecutions;

public class ExecuteOptimizationConfigurationResult {

	private OptimizationJobExecutions optimizationJobExecution;
	private String message;

	public ExecuteOptimizationConfigurationResult() {

	}

	public ExecuteOptimizationConfigurationResult(OptimizationJobExecutions optimizationJobExecution, String message) {
		this.optimizationJobExecution = optimizationJobExecution;
		this.message = message;
	}

	public OptimizationJobExecutions getOptimizationJobExecution() {
		return optimizationJobExecution;
	}

	public void setOptimizationJobExecution(OptimizationJobExecutions optimizationJobExecution) {
		this.optimizationJobExecution = optimizationJobExecution;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
