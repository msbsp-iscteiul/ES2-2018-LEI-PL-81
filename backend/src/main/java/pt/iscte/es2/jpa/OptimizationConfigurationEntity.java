package pt.iscte.es2.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "optimization_configuration")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class OptimizationConfigurationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String problemName;

	@NotBlank
	private String email;

	@NotBlank
	private String filePath;

	@NotNull
	private Integer variablesQuantity;

	@NotNull
	private Integer objectivesQuantity;

	private Integer restrictionsQuantity;

	@NotNull
	private Integer algorithmsQuantity;

	@NotNull
	private Integer executionMaxWaitTime;

	@NotNull
	private String algorithmChoiceMethod;

	@NotNull
	private String description;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationConfigurationVariablesEntity> optimizationConfigurationVariables;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationConfigurationObjectivesEntity> optimizationConfigurationObjectives;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationConfigurationRestrictionsEntity> optimizationConfigurationRestrictions;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationConfigurationAlgorithmsEntity> optimizationConfigurationAlgorithms;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationConfigurationUserSolutionsEntity> optimizationConfigurationUserSolutions;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private List<OptimizationJobExecutionsEntity> optimizationJobExecutions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAlgorithmChoiceMethod() {
		return algorithmChoiceMethod;
	}

	public void setAlgorithmChoiceMethod(String algorithmChoiceMethod) {
		this.algorithmChoiceMethod = algorithmChoiceMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OptimizationConfigurationVariablesEntity> getOptimizationConfigurationVariables() {
		if (optimizationConfigurationVariables == null) {
			optimizationConfigurationVariables = new ArrayList<>();
		}
		return optimizationConfigurationVariables;
	}

	public void setOptimizationConfigurationVariables(List<OptimizationConfigurationVariablesEntity> optimizationConfigurationVariables) {
		this.optimizationConfigurationVariables = optimizationConfigurationVariables;
	}

	public List<OptimizationConfigurationObjectivesEntity> getOptimizationConfigurationObjectives() {
		if (optimizationConfigurationObjectives == null) {
			optimizationConfigurationObjectives = new ArrayList<>();
		}
		return optimizationConfigurationObjectives;
	}

	public void setOptimizationConfigurationObjectives(List<OptimizationConfigurationObjectivesEntity> optimizationConfigurationObjectives) {
		this.optimizationConfigurationObjectives = optimizationConfigurationObjectives;
	}

	public List<OptimizationConfigurationRestrictionsEntity> getOptimizationConfigurationRestrictions() {
		if (optimizationConfigurationRestrictions == null) {
			optimizationConfigurationRestrictions = new ArrayList<>();
		}
		return optimizationConfigurationRestrictions;
	}

	public void setOptimizationConfigurationRestrictions(List<OptimizationConfigurationRestrictionsEntity> optimizationConfigurationRestrictions) {
		this.optimizationConfigurationRestrictions = optimizationConfigurationRestrictions;
	}

	public List<OptimizationConfigurationAlgorithmsEntity> getOptimizationConfigurationAlgorithms() {
		if (optimizationConfigurationAlgorithms == null) {
			optimizationConfigurationAlgorithms = new ArrayList<>();
		}
		return optimizationConfigurationAlgorithms;
	}

	public void setOptimizationConfigurationAlgorithms(List<OptimizationConfigurationAlgorithmsEntity> optimizationConfigurationAlgorithms) {
		this.optimizationConfigurationAlgorithms = optimizationConfigurationAlgorithms;
	}

	public List<OptimizationConfigurationUserSolutionsEntity> getOptimizationConfigurationUserSolutions() {
		if (optimizationConfigurationUserSolutions == null) {
			optimizationConfigurationUserSolutions = new ArrayList<>();
		}
		return optimizationConfigurationUserSolutions;
	}

	public void setOptimizationConfigurationUserSolutions(List<OptimizationConfigurationUserSolutionsEntity> optimizationConfigurationUserSolutions) {
		this.optimizationConfigurationUserSolutions = optimizationConfigurationUserSolutions;
	}

	public List<OptimizationJobExecutionsEntity> getOptimizationJobExecutions() {
		if (optimizationJobExecutions == null) {
			optimizationJobExecutions = new ArrayList<>();
		}
		return optimizationJobExecutions;
	}

	public void setOptimizationJobExecutions(List<OptimizationJobExecutionsEntity> optimizationJobExecutions) {
		this.optimizationJobExecutions = optimizationJobExecutions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
