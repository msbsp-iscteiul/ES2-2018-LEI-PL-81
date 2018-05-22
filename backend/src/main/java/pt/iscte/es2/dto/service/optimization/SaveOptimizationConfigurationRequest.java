package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Save OptimizationConfiguration Request
 *
 * Contains an OptimizationConfiguration to be Persisted, suggested by the User
 * 		problemName - Just a simple problem name
 * 		description - Description of what the problem consists
 * 		email - Email of the user that is submitting the OptimizationConfiguration
 * 		sessionId - Unique Session ID
 * 		variables - List of {@link OptimizationConfigurationVariables} of the problem
 * 		objectives - List of {@link OptimizationConfigurationObjectives} of the problem
 * 		algorithms - List of {@link OptimizationConfigurationAlgorithms} of the problem
 * 		restrictions - List of {@link OptimizationConfigurationRestrictions} of the problem
 * 		algorithmChoiceMethod - {@link AlgorithmChoiceMethod} on how the problem should be ran
 * 		executionMaxWaitTime - Max wait time for the execution to complete
 * 		file - CVS File that the User can submit with the his Solutions for the problem to be solved
 */
public class SaveOptimizationConfigurationRequest {

	private String problemName;
	private String description;
	private String email;
	private String sessionId;
	private List<OptimizationConfigurationVariables> variables;
	private List<OptimizationConfigurationObjectives> objectives;
	private List<OptimizationConfigurationAlgorithms> algorithms;
	private List<OptimizationConfigurationRestrictions> restrictions;
	private AlgorithmChoiceMethod algorithmChoiceMethod;
	private Integer executionMaxWaitTime;
	private MultipartFile file;

	public SaveOptimizationConfigurationRequest() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<OptimizationConfigurationVariables> getVariables() {
		if (variables == null) {
			this.variables = new ArrayList<>();
		}
		return variables;
	}

	public void setVariables(List<OptimizationConfigurationVariables> variables) {
		this.variables = variables;
	}

	public List<OptimizationConfigurationObjectives> getObjectives() {
		if (objectives == null) {
			this.objectives = new ArrayList<>();
		}
		return objectives;
	}

	public void setObjectives(List<OptimizationConfigurationObjectives> objectives) {
		this.objectives = objectives;
	}

	public List<OptimizationConfigurationAlgorithms> getAlgorithms() {
		if (algorithms == null) {
			this.algorithms = new ArrayList<>();
		}
		return algorithms;
	}

	public void setAlgorithms(List<OptimizationConfigurationAlgorithms> algorithms) {
		this.algorithms = algorithms;
	}

	public AlgorithmChoiceMethod getAlgorithmChoiceMethod() {
		return algorithmChoiceMethod;
	}

	public void setAlgorithmChoiceMethod(AlgorithmChoiceMethod algorithmChoiceMethod) {
		this.algorithmChoiceMethod = algorithmChoiceMethod;
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

	public Integer getExecutionMaxWaitTime() {
		return executionMaxWaitTime;
	}

	public void setExecutionMaxWaitTime(Integer executionMaxWaitTime) {
		this.executionMaxWaitTime = executionMaxWaitTime;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
