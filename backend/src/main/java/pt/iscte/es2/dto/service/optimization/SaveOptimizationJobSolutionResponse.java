package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationJobSolutions;

/**
 * DTO Save OptimizationJobSolution Response
 *
 * Contains a {@link SaveOptimizationJobSolutionResult} from the resulting saved {@link OptimizationJobSolutions}
 */
public class SaveOptimizationJobSolutionResponse {

	private SaveOptimizationJobSolutionResult result;

	public SaveOptimizationJobSolutionResponse() {

	}

	public SaveOptimizationJobSolutionResponse(SaveOptimizationJobSolutionResult result) {

	}

	public SaveOptimizationJobSolutionResult getResult() {
		return result;
	}

	public void setResult(SaveOptimizationJobSolutionResult result) {
		this.result = result;
	}
}
