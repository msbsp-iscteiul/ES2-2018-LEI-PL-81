package pt.iscte.es2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO of OptimizationConfiguration
 */
public class OptimizationConfiguration {

	private Integer id;
	private String problemName;
	private String email;
	private String filePath;
	private Integer variablesQuantity;
	private Integer objectivesQuantity;
	private Integer restrictionsQuantity;
	private Integer algorithmsQuantity;
	private Integer executionMaxWaitTime;
	private String description;
	private AlgorithmChoiceMethod algorithmChoiceMethod;
	private List<OptimizationConfigurationVariables> variables;
	private List<OptimizationConfigurationObjectives> objectives;
	private List<OptimizationConfigurationRestrictions> restrictions;
	private List<OptimizationConfigurationAlgorithms> algorithms;
	private List<OptimizationConfigurationUserSolutions> userSolutions;
	private List<OptimizationJobExecutions> executions;

	public OptimizationConfiguration() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getVariablesQuantity() {
		return variablesQuantity;
	}

	public void setVariablesQuantity(Integer variablesQuantity) {
		this.variablesQuantity = variablesQuantity;
	}

	public Integer getObjectivesQuantity() {
		return objectivesQuantity;
	}

	public void setObjectivesQuantity(Integer objectivesQuantity) {
		this.objectivesQuantity = objectivesQuantity;
	}

	public Integer getRestrictionsQuantity() {
		return restrictionsQuantity;
	}

	public void setRestrictionsQuantity(Integer restrictionsQuantity) {
		this.restrictionsQuantity = restrictionsQuantity;
	}

	public Integer getAlgorithmsQuantity() {
		return algorithmsQuantity;
	}

	public void setAlgorithmsQuantity(Integer algorithmsQuantity) {
		this.algorithmsQuantity = algorithmsQuantity;
	}

	public Integer getExecutionMaxWaitTime() {
		return executionMaxWaitTime;
	}

	public void setExecutionMaxWaitTime(Integer executionMaxWaitTime) {
		this.executionMaxWaitTime = executionMaxWaitTime;
	}

	public AlgorithmChoiceMethod getAlgorithmChoiceMethod() {
		return algorithmChoiceMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAlgorithmChoiceMethod(AlgorithmChoiceMethod algorithmChoiceMethod) {
		this.algorithmChoiceMethod = algorithmChoiceMethod;
	}

	public List<OptimizationConfigurationVariables> getVariables() {
		if (variables == null) {
			variables = new ArrayList<>();
		}
		return variables;
	}

	public void setVariables(List<OptimizationConfigurationVariables> variables) {
		this.variables = variables;
	}

	public List<OptimizationConfigurationObjectives> getObjectives() {
		if (objectives == null) {
			objectives = new ArrayList<>();
		}
		return objectives;
	}

	public void setObjectives(List<OptimizationConfigurationObjectives> objectives) {
		this.objectives = objectives;
	}

	public List<OptimizationConfigurationRestrictions> getRestrictions() {
		if (restrictions == null) {
			restrictions = new ArrayList<>();
		}
		return restrictions;
	}

	public void setRestrictions(List<OptimizationConfigurationRestrictions> restrictions) {
		this.restrictions = restrictions;
	}

	public List<OptimizationConfigurationAlgorithms> getAlgorithms() {
		if (algorithms == null) {
			algorithms = new ArrayList<>();
		}
		return algorithms;
	}

	public void setAlgorithms(List<OptimizationConfigurationAlgorithms> algorithms) {
		this.algorithms = algorithms;
	}

	public List<OptimizationConfigurationUserSolutions> getUserSolutions() {
		if (userSolutions == null) {
			userSolutions = new ArrayList<>();
		}
		return userSolutions;
	}

	public void setUserSolutions(List<OptimizationConfigurationUserSolutions> userSolutions) {
		this.userSolutions = userSolutions;
	}

	public List<OptimizationJobExecutions> getExecutions() {
		if (executions == null) {
			executions = new ArrayList<>();
		}
		return executions;
	}

	public void setExecutions(List<OptimizationJobExecutions> executions) {
		this.executions = executions;
	}
}
