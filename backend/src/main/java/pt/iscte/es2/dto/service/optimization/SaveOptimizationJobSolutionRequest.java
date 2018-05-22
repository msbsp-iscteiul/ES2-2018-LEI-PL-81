package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.OptimizationJobSolutions;
import pt.iscte.es2.dto.State;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Save OptimizationJobSolution Request
 *
 * Request to save more than one {@link OptimizationJobSolutions},
 * containing the id of the {@link OptimizationJobExecutions}, the state {@link State}, list of solutions as mentioned,
 * Latex File {@link MultipartFile} and R File {@link MultipartFile}.
 * These 2 files come from the evalutions of JMetal Framework which generates them.
 */
public class SaveOptimizationJobSolutionRequest {

	private Integer id;
	private State state;
	private List<OptimizationJobSolutions> solutions;
	private MultipartFile latex;
	private MultipartFile r;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	public MultipartFile getLatex() {
		return latex;
	}

	public void setLatex(MultipartFile latex) {
		this.latex = latex;
	}

	public MultipartFile getR() {
		return r;
	}

	public void setR(MultipartFile r) {
		this.r = r;
	}
}
