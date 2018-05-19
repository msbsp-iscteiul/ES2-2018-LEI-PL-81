package pt.iscte.es2.optimization_job_runner.post_processing;

import pt.iscte.es2.optimization_job_runner.post_processing.result_compilers.TexToPdfCompiler;

import java.io.File;
import java.io.IOException;

/**
 * Processes the latex file to generate the EPS file
 */
public class LatexToPdfProcessor implements PostProblemProcessor {
	/**
	 * Processes the latex file to generate the EPS file
	 * @param result the job result
	 */
	@Override
	public void process(OptimizationJobResult result) {
		try {
			final PostProcessingContext context = result.getContext();
			final File latexPdf = new TexToPdfCompiler().compile(
				context.getExperimentPath() + "/latex/" + context.getExperimentName() + ".tex");
			result.setLatexPdf(latexPdf.getAbsolutePath());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
