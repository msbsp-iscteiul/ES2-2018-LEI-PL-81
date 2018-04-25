package pt.iscte.es2.dto;

import java.io.Serializable;
import java.util.Date;

public class HistoryNode implements Serializable {

	private String problemName;
	private Date startDate;
	private Date endDate;

	public HistoryNode() {

	}

	public HistoryNode(String problemName, Date startDate, Date endDate) {
		this.problemName = problemName;
		this.startDate = startDate;
		this.endDate = endDate;
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
}
