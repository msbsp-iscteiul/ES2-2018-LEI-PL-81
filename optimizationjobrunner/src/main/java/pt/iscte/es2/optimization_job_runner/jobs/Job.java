package pt.iscte.es2.optimization_job_runner.jobs;

import javax.persistence.*;

@Entity
@Table(name="optimization_job_executions")
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id = 1L;
	private String state;

	public Job(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void complete() {
		state = "Finished";
	}

	public long getWaitingTime() {
		return 9999999L;
	}
}
