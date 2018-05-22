package pt.iscte.es2.optimization_job_runner.post_processing.result_compilers;

import java.io.File;
import java.io.IOException;

/**
 * Compile R to EPS
 */
public class RToEpsCompiler {

	/**
	 * Compiles the R file, generated from NSGAII Algorithm, creating the EPS file.
	 * @return File
	 * @throws IOException
	 */
	public File compile(String texFileName) throws IOException, InterruptedException {
		final File experimentPathFile = new File(texFileName);
		final String fileNameWithoutExtension = experimentPathFile.getName().replaceFirst("[.][^.]+$", "");
		Runtime.getRuntime()
			.exec(
				"Rscript " + experimentPathFile.getAbsolutePath(),
				null,
				experimentPathFile.getParentFile()
			).waitFor();
		return new File(experimentPathFile.getParentFile().getAbsolutePath() + "/" + fileNameWithoutExtension + ".eps");
	}
}
