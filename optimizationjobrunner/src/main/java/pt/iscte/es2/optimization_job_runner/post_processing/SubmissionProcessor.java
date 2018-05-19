package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.jobs.BackendGateway;

/**
 * Submits the result to the backend
 */
public class SubmissionProcessor implements PostProblemProcessor {

	private final BackendGateway backendGateway;

	/**
	 * Constructor
	 *
	 * @param backendGateway backend gateway
	 */
	public SubmissionProcessor(BackendGateway backendGateway) {
		this.backendGateway = backendGateway;
	}

	/**
	 * Submits the result to the backend
	 *
	 * @param result the subject for processing
	 */
	@Override
	public void process(OptimizationJobResult result) {
		backendGateway.saveFinishedOptimizationJobSolution(result);
	}
}
