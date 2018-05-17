package pt.iscte.es2.optimization_job_runner.post_processing;

/**
 * Defines the contract for processing {@link OptimizationJobResult}s
 */
public interface PostProblemProcessor {
	/**
	 * Processes a {@link OptimizationJobResult}
	 * @param result the subject for processing
	 */
	void process(OptimizationJobResult result);
}
