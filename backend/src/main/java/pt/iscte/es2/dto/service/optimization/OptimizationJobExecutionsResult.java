package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationJobExecutionSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO OptimizationJobExecutions Result
 *
 * Result that contains a List of {@link OptimizationJobExecutionSummary}, which consist on a summary of the executions
 */
public class OptimizationJobExecutionsResult {

	private List<OptimizationJobExecutionSummary> executions;

	public OptimizationJobExecutionsResult() {

	}

	public List<OptimizationJobExecutionSummary> getExecutions() {
		if (executions == null) {
			executions = new ArrayList<>();
		}
		return executions;
	}

	public void setExecutions(List<OptimizationJobExecutionSummary> executions) {
		this.executions = executions;
	}
}
