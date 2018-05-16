package pt.iscte.es2.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for the Optimization Job Executions
 */
public class OptimizationJobExecutions {

	private Integer id;
	private Date startDate;
	private Date endDate;
	private State state;
	private OptimizationConfiguration optimizationConfiguration;
	private List<OptimizationJobSolutions> optimizationJobSolutions;

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

	public List<OptimizationJobSolutions> getOptimizationJobSolutions() {
		if (optimizationJobSolutions == null) {
			optimizationJobSolutions = new ArrayList<>();
		}
		return optimizationJobSolutions;
	}

	public void setOptimizationJobSolutions(List<OptimizationJobSolutions> optimizationJobSolutions) {
		this.optimizationJobSolutions = optimizationJobSolutions;
	}

	public OptimizationConfiguration getOptimizationConfiguration() {
		return optimizationConfiguration;
	}

	public void setOptimizationConfiguration(OptimizationConfiguration optimizationConfiguration) {
		this.optimizationConfiguration = optimizationConfiguration;
	}
}
