package pt.iscte.es2.optimization_job_runner;

import org.springframework.stereotype.Component;
import pt.iscte.es2.optimization_job_runner.jobs.BackendGateway;
import pt.iscte.es2.optimization_job_runner.jobs.Job;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The rabbitmq message receiver
 */
@Component
public class Receiver {

	private static final Logger LOGGER = Logger.getLogger(Receiver.class.getName());

	private final JMetalTaskRunner jMetalTaskRunner;
	private final BackendGateway backendGateway;

	/**
	 * Constructor
	 * @param jMetalTaskRunner the jmetal task executor
	 * @param backendGateway
	 */
	public Receiver(JMetalTaskRunner jMetalTaskRunner, BackendGateway backendGateway) {
		this.jMetalTaskRunner = jMetalTaskRunner;
		this.backendGateway = backendGateway;
	}

	/**
	 * Receive a message
	 * @param idsEmailStruct the message
	 */
	public void receiveMessage(Object[] idsEmailStruct) throws IOException, ExecutionException, InterruptedException {
		long configurationId = (long) idsEmailStruct[0];
		long jobId = (long) idsEmailStruct[1];
		String email = (String) idsEmailStruct[2];
		Job job = backendGateway.getConfigurationOfId(configurationId, jobId, email);
		LOGGER.log(Level.INFO, email + " " + configurationId);
		jMetalTaskRunner.execute(job);
	}
}
