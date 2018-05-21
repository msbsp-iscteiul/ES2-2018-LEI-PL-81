package pt.iscte.es2.optimization_job_runner.jobs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OptimizationConfigurationDeserializer.class)
public class OptimizationConfiguration {

	private final String email;
	private final int waitingTime;
	private final String problemName;

	public OptimizationConfiguration(String email, int waitingTime, String problemName) {
		this.email = email;
		this.waitingTime = waitingTime;
		this.problemName = problemName;
	}

	public String getEmail() {
		return email;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public String getProblemName() {
		return problemName;
	}
}
