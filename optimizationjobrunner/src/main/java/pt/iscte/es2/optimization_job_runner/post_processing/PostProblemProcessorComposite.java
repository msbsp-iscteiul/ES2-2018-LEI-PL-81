package pt.iscte.es2.optimization_job_runner.post_processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostProblemProcessorComposite implements PostProblemProcessor {

	private final List<PostProblemProcessor> processors;

	public PostProblemProcessorComposite(List<PostProblemProcessor> processors) {
		this.processors = processors == null ? new ArrayList<>() : processors;
	}

	public void process(OptimizationJobResult result) {
		processors.forEach(postProblemProcessor -> postProblemProcessor.process(result));
	}
}
