package pt.iscte.es2.optimization_job_runner.post_processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Composite processor for processing {@link OptimizationJobResult}
 */
public class PostProblemProcessorComposite implements PostProblemProcessor {

	private final List<PostProblemProcessor> processors;

	/**
	 * Constructor
	 * @param processors list of processors for the composite
	 */
	public PostProblemProcessorComposite(List<PostProblemProcessor> processors) {
		this.processors = processors == null ? new ArrayList<>() : processors;
	}

	/**
	 * Processes with the processors in the composite
	 * @param result the subject for processing
	 */
	@Override
	public void process(OptimizationJobResult result) {
		processors.forEach(postProblemProcessor -> postProblemProcessor.process(result));
	}
}
