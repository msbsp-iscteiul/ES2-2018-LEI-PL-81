package pt.iscte.es2.optimization_job_runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pt.iscte.es2.optimization_job_runner.io.Utils;
import pt.iscte.es2.optimization_job_runner.jobs.Job;
import pt.iscte.es2.optimization_job_runner.mail.MailSender;
import pt.iscte.es2.optimization_job_runner.post_processing.OptimizationJobResult;
import pt.iscte.es2.optimization_job_runner.post_processing.PostProblemProcessor;
import pt.iscte.es2.optimization_job_runner.post_processing.PostProblemProcessorComposite;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.*;
import java.util.logging.Logger;

@Component
public class JMetalConfiguration {

	private static final Logger LOGGER = Logger.getLogger(JMetalConfiguration.class.getName());
	private final MailSender mailSender;
	private final PostProblemProcessor postProblemProcessor;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	@Value("${jmetal.experiment_name}")
	private String experimentName;
	@Value("${jmetal.experiment_base_directory}")
	private String experimentBaseDirectory;
	@Value("${jmetal.reference_fronts}")
	private String referenceFront;

	public JMetalConfiguration(MailSender mailSender, PostProblemProcessor postProblemProcessor) {
		this.mailSender = mailSender;
		this.postProblemProcessor = postProblemProcessor;
	}

	public void execute(Job job) throws ExecutionException, InterruptedException {
		LOGGER.info("Cleaning dir...");
		cleanup();
		try {
			final Future<OptimizationJobResult> result = executor.submit(new JMetalTask(
				mailSender, job, experimentName, experimentBaseDirectory, referenceFront
			));
			final OptimizationJobResult optimizationJobResult = result.get(
				job.getWaitingTime(), TimeUnit.SECONDS);
			postProblemProcessor.process(optimizationJobResult);
		} catch (TimeoutException e) {
			handleTimeout();
		}
	}

	private void handleTimeout() {
		LOGGER.warning("Task timeout");
		executor.shutdownNow();
		try {
			LOGGER.warning("awaiting for termination...");
			executor.awaitTermination(10L, TimeUnit.SECONDS);
		} catch (InterruptedException ignore) {
			LOGGER.info("Interrupted exception...");
		}
		executor = Executors.newSingleThreadExecutor();
		LOGGER.info("Sending timeout email...");
		sendTimeoutEmail();
	}

	private void cleanup() {
		Utils.deleteDir(new File(experimentBaseDirectory));
	}

	private void sendTimeoutEmail() {
		mailSender.send(
			"A execução da tarefa excedeu o tempo máximo definido",
			"A execução da tarefa excedeu o tempo máximo definido.",
			"mauro.s.pinto@gmail.com" // TODO email
		);
	}
}
