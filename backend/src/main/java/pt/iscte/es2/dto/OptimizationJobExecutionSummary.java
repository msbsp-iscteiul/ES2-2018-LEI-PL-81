package pt.iscte.es2.dto;

import java.util.Date;

/**
 * DTO of OptimizationJobExecutionSummary
 *
 * Consists of a Summary that contains an executionId, optimizationConfigurationId,
 * problemName, startDate, endDate and state of the Execution
 */
public class OptimizationJobExecutionSummary {

	private Integer executionId;
	private Integer optimizationConfigurationId;
	private String problemName;
	private Date startDate;
	private Date endDate;
	private String state;

	public OptimizationJobExecutionSummary() {

	}

	public Integer getExecutionId() {
		return executionId;
	}

	public void setExecutionId(Integer executionId) {
		this.executionId = executionId;
	}

	public Integer getOptimizationConfigurationId() {
		return optimizationConfigurationId;
	}

	public void setOptimizationConfigurationId(Integer optimizationConfigurationId) {
		this.optimizationConfigurationId = optimizationConfigurationId;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
