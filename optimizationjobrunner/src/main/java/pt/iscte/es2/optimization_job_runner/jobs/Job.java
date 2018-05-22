package pt.iscte.es2.optimization_job_runner.jobs;

public class Job {
	private long id;
	private final String problemName;
	private final String jarPath;
	private final String userEmail;
	private final int waitingTime;

	public Job(long id, String problemName, String jarPath, String userEmail, int waitingTime) {
		this.id = id;
		this.problemName = problemName;
		this.jarPath = jarPath;
		this.userEmail = userEmail;
		this.waitingTime = waitingTime;
	}

	public long getId() {
		return id;
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
}
