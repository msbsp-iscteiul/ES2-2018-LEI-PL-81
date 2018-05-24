package pt.iscte.es2.optimization_job_runner.jobs;

import java.util.List;

public class Job {
	private long jobId;
	private final String problemName;
	private final String jarPath;
	private final String userEmail;
	private final int waitingTime;
	private final List<String> algorithms;

	public Job(long jobId, String problemName, String jarPath, String userEmail, int waitingTime, List<String> algorithms) {
		this.jobId = jobId;
		this.problemName = problemName;
		this.jarPath = jarPath;
		this.userEmail = userEmail;
		this.waitingTime = waitingTime;
		this.algorithms = algorithms;
	}

	public long getJobId() {
		return jobId;
	}

	public String getProblemName() {
		return problemName;
	}

	public String getJarPath() {
		return jarPath;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public List<String> getAlgorithms() {
		return algorithms;
	}
}
