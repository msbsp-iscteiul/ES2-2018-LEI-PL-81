package pt.iscte.es2.optimization_job_runner;

import org.springframework.stereotype.Component;
import pt.iscte.es2.optimization_job_runner.jobs.Job;
import pt.iscte.es2.optimization_job_runner.jobs.JobRepository;

import java.util.concurrent.ExecutionException;

@Component
public class Receiver {

	private final JMetalConfiguration jMetalConfiguration;
	private final JobRepository jobRepository;

	public Receiver(JMetalConfiguration jMetalConfiguration, JobRepository jobRepository) {
		this.jMetalConfiguration = jMetalConfiguration;
		this.jobRepository = jobRepository;
	}

	public void receiveMessage(String jobId) throws ExecutionException, InterruptedException {
		System.out.println("Received <" + jobId + ">");
//		final Job awaitingWithId = jobRepository.findAwaitingWithId(Long.valueOf(jobId));
//		System.out.println(awaitingWithId);
		jMetalConfiguration.execute(new Job());
	}
}
