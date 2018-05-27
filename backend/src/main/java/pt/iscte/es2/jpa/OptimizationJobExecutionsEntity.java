package pt.iscte.es2.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.iscte.es2.dto.State;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_optimization_job_executions", nullable = false)
	private List<OptimizationJobSolutionsEntity> optimizationJobSolutions;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	private State state;

	private String latexPath;

	private String rPath;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	public OptimizationJobExecutionsEntity() {

	}

	public OptimizationJobExecutionsEntity(Date startDate) {
		this.startDate = startDate;
		this.state = State.Ready;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OptimizationJobSolutionsEntity> getOptimizationJobSolutions() {
		if (optimizationJobSolutions == null) {
			optimizationJobSolutions = new ArrayList<>();
		}
		return optimizationJobSolutions;
	}

	public void setOptimizationJobSolutions(List<OptimizationJobSolutionsEntity> optimizationJobSolutions) {
		this.optimizationJobSolutions.addAll(optimizationJobSolutions);
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getLatexPath() {
		return latexPath;
	}

	public void setLatexPath(String latexPath) {
		this.latexPath = latexPath;
	}

	public String getrPath() {
		return rPath;
	}

	public void setrPath(String rPath) {
		this.rPath = rPath;
	}
}
