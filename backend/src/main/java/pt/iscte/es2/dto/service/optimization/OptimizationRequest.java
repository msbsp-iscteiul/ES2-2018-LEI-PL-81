package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Optimization Request
 */
public class OptimizationRequest {

	private String problemName;
	private String email;
	private String sessionId;
	private List<OptimizationConfigurationVariables> variables;
	private List<OptimizationConfigurationObjectives> objectives;
	private List<OptimizationConfigurationAlgorithms> algorithms;
	private List<OptimizationConfigurationRestrictions> restrictions;
	private AlgorithmChoiceMethod algorithmChoiceMethod;
	private Integer executionMaxWaitTime;
	private MultipartFile file;

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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<OptimizationConfigurationVariables> getVariables() {
		return variables;
	}

	public void setVariables(List<OptimizationConfigurationVariables> variables) {
		this.variables = variables;
	}

	public List<OptimizationConfigurationObjectives> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<OptimizationConfigurationObjectives> objectives) {
		this.objectives = objectives;
	}

	public List<OptimizationConfigurationAlgorithms> getAlgorithms() {
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
