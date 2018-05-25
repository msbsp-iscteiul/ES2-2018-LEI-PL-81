package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationJobSolutions;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Save OptimizationJobSolution Result
 *
 * Contains a {@link String} for a message of Success or Failure and a List of {@link OptimizationJobSolutions}
 * corresponding to the persisted solutions.
 */
public class SaveOptimizationJobSolutionResult {

	private String message;
	private List<OptimizationJobSolutions> solutions;

	public SaveOptimizationJobSolutionResult(String message) {
		this.message = message;
	}

	public SaveOptimizationJobSolutionResult(String message, List<OptimizationJobSolutions> solutions) {
		this.message = message;
		this.solutions = solutions;
	}

	public List<OptimizationJobSolutions> getSolutions() {
		if (solutions == null) {
			solutions = new ArrayList<>();
		}
		return solutions;
	}

	public void setSolutions(List<OptimizationJobSolutions> solutions) {
		this.solutions = solutions;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
