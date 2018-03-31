package pt.iscte.es2.dto.serviceview.upload;

/**
 * DTO Upload Result
 */
public class UploadResult {

	private int variables;
	private int objectives;
	private int constraints;

	public UploadResult() {

	}

	public UploadResult(int variables, int objectives, int constraints) {
		this.variables = variables;
		this.objectives = objectives;
		this.constraints = constraints;
	}

	public int getVariables() {
		return variables;
	}

	public void setVariables(int variables) {
		this.variables = variables;
	}

	public int getObjectives() {
		return objectives;
	}

	public void setObjectives(int objectives) {
		this.objectives = objectives;
	}

	public int getConstraints() {
		return constraints;
	}

	public void setConstraints(int constraints) {
		this.constraints = constraints;
	}
}
