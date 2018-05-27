package pt.iscte.es2.optimization_job_runner.jobs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = OptimizationConfigurationDeserializer.class)
public class OptimizationConfiguration {

	private final String email;
	private final int waitingTime;
	private final String problemName;
	private final List<String> algorithms;

	public OptimizationConfiguration(String email, int waitingTime, String problemName, List<String> algorithms) {
		this.email = email;
		this.waitingTime = waitingTime;
		this.problemName = problemName;
		this.algorithms = algorithms;
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

	public List<String> getAlgorithms() {
		return algorithms;
	}
}
