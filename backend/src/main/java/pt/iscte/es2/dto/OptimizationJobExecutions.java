package pt.iscte.es2.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO of OptimizationJobExecutions
 *
 * Execution DTO that contains the id, startDate, endDate, state and possibly
 * a list of solutions {@link OptimizationJobSolutions}
 */
public class OptimizationJobExecutions {

	private Integer id;
	private Date startDate;
	private Date endDate;
	private State state;
	private List<OptimizationJobSolutions> solutions;

	public OptimizationJobExecutions() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
}
