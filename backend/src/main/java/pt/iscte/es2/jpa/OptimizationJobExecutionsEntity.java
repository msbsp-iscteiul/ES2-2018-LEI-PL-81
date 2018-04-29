package pt.iscte.es2.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.iscte.es2.dto.State;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "optimization_job_executions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class OptimizationJobExecutionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_optimization_configuration", nullable = false)
	private OptimizationConfigurationEntity optimizationConfiguration;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "optimizationJobExecutions")
	private List<OptimizationJobSolutionsEntity> optimizationJobSolutions;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private Enum<State> state;

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

	public OptimizationConfigurationEntity getOptimizationConfiguration() {
		return optimizationConfiguration;
	}

	public void setOptimizationConfiguration(OptimizationConfigurationEntity optimizationConfiguration) {
		this.optimizationConfiguration = optimizationConfiguration;
	}

	public List<OptimizationJobSolutionsEntity> getOptimizationJobSolutions() {
		return optimizationJobSolutions;
	}

	public void setOptimizationJobSolutions(List<OptimizationJobSolutionsEntity> optimizationJobSolutions) {
		this.optimizationJobSolutions = optimizationJobSolutions;
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

	public Enum<State> getState() {
		return state;
	}

	public void setState(Enum<State> state) {
		this.state = state;
	}
}
