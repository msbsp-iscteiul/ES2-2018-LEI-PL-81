package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.post_processing.result_compilers.RToEpsCompiler;

import java.io.File;
import java.io.IOException;

/**
 * Processes the R file to generate the EPS file
 */
public class RToEpsProcessor implements PostProblemProcessor {
	/**
	 * Processes the R file to generate the EPS file
	 * @param result the subject for processing
	 */
	@Override
	public void process(OptimizationJobResult result) {
		try {
			final PostProcessingContext context = result.getContext();
			final File rEps = new RToEpsCompiler().compile(context.getExperimentPath() + "/R/HV.Boxplot.R");
			result.setrEps(rEps.getAbsolutePath());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
