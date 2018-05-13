package pt.iscte.es2.optimization_job_runner.jobs;

import javax.persistence.*;

@Entity
@Table(name="optimization_job_executions")
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String state;

	public Long getId() {
		return id;
	}

	public void complete() {
		state = "Finished";
	}
}
