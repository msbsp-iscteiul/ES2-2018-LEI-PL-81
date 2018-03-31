package pt.iscte.es2.dto.serviceview.upload;

/**
 * DTO Upload Response
 */
public class UploadResponse {

	private String sessionId;
	private Integer variables;
	private Integer objectives;
	private Integer constraints;

	public UploadResponse() {

	}

	public UploadResponse(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getVariables() {
		return variables;
	}

	public void setVariables(Integer variables) {
		this.variables = variables;
	}

	public Integer getObjectives() {
		return objectives;
	}

	public void setObjectives(Integer objectives) {
		this.objectives = objectives;
	}

	public Integer getConstraints() {
		return constraints;
	}

	public void setConstraints(Integer constraints) {
		this.constraints = constraints;
	}
}
