package pt.iscte.es2.dto.serviceview.upload;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Upload Result
 */
public class UploadResult {

	private int id;
	private int variables;
	private int objectives;
	private List<String> algorithms;

	public UploadResult() {

	}

	public UploadResult(int id, int variables, int objectives) {
		this.id = id;
		this.variables = variables;
		this.objectives = objectives;
	}

	public List<String> getAlgorithms() {
		if (algorithms == null) {
			algorithms = new ArrayList<>();
		}
		return algorithms;
	}

	public void setAlgorithms(List<String> algorithms) {
		this.algorithms = algorithms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
