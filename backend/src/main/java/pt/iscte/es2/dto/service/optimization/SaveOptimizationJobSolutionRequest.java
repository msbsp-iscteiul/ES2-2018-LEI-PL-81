package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationJobSolutions;

import java.util.ArrayList;
import java.util.List;

public class SaveOptimizationJobSolutionRequest {

	private Integer id;
	private List<OptimizationJobSolutions> solutions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
