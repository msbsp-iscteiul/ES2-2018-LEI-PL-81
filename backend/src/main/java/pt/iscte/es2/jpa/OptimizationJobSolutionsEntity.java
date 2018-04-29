package pt.iscte.es2.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "optimization_job_solutions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class OptimizationJobSolutionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_optimization_job_executions", nullable = false)
	private OptimizationJobExecutionsEntity optimizationJobExecutions;

	@NotBlank
	private String algorithmName;

	@NotBlank
	private String solutionName;

	@NotBlank
	private String solutionQuality;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OptimizationJobExecutionsEntity getOptimizationJobExecutions() {
		return optimizationJobExecutions;
	}

	public void setOptimizationJobExecutions(OptimizationJobExecutionsEntity optimizationJobExecutions) {
		this.optimizationJobExecutions = optimizationJobExecutions;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getSolutionQuality() {
		return solutionQuality;
	}

	public void setSolutionQuality(String solutionQuality) {
		this.solutionQuality = solutionQuality;
	}
}
