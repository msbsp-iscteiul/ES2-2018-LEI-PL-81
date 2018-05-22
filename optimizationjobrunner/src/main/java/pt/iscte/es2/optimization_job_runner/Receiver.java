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
	 * @param idEmailStruct the message
	 */
	public void receiveMessage(Object[] idEmailStruct) throws IOException, ExecutionException, InterruptedException {
		long id = (long) idEmailStruct[0];
		String email = (String) idEmailStruct[1];
		Job job = backendGateway.getConfigurationOfId(id, email);
		LOGGER.log(Level.INFO, email + " " + id);
		jMetalTaskRunner.execute(job);
	}
}
