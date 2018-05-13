package pt.iscte.es2.optimization_job_runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pt.iscte.es2.optimization_job_runner.jobs.Job;
import pt.iscte.es2.optimization_job_runner.jobs.JobRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component
public class Poller {

	@Autowired
	JobRepository repository;

	@Bean
	public CommandLineRunner demo() {
		return this::run;
	}

	private void run(String... args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		do {
			// find and change to running?
			final List<Job> jobsAwaiting = repository.findAwaiting();
			jobsAwaiting.forEach(job -> executorService.submit(() -> {
				job.complete();
				repository.save(job);
			}));
			Thread.sleep(1000);
		} while (true);
	}
}
