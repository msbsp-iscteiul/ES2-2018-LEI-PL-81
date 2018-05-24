package pt.iscte.es2.dto.service.optimization;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO FileUpload Result
 *
 * Contains the number of Variables, Objectives, VariableType and the Algorithms for the problem uploaded by the User
 */
public class FileUploadResult {

	private int variables;
	private int objectives;
	private List<String> algorithms;
	private String variable_type;

	public FileUploadResult() {

	}

	public FileUploadResult(int id, int variables, int objectives) {
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

	public String getVariable_type() {
		return variable_type;
	}

	public void setVariable_type(String variable_type) {
		this.variable_type = variable_type;
	}
}
