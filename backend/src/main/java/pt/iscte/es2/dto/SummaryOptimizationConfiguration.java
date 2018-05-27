package pt.iscte.es2.dto;

import java.util.Date;

/**
 * DTO of SummaryOptimizationConfiguration
 *
 * Contains a Summary of an {@link OptimizationConfiguration}
 */
public class SummaryOptimizationConfiguration {

	private Integer id;
	private String problemName;
	private String description;
	private Date createdDate;

	public SummaryOptimizationConfiguration() {

	}

	public SummaryOptimizationConfiguration(Integer id, String problemName, String description, Date createdDate) {
		this.id = id;
		this.problemName = problemName;
		this.description = description;
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
