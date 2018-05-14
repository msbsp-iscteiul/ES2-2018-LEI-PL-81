package pt.iscte.es2.optimization_job_runner.post_processing.result_compilers;

import java.io.File;
import java.io.IOException;

/**
 * Compile Tex to PDF
 */
public class TexToPdfCompiler {

	/**
	 * Compiles the Tex file, generated from NSGAII Algorithm, creating the PDF file.
	 * @return File
	 * @throws IOException
	 */
	public File compile(String texFileName) throws IOException, InterruptedException {
		final File experimentPathFile = new File(texFileName);
		final String fileNameWithoutExtension = experimentPathFile.getName().replaceFirst("[.][^.]+$", "");
		Runtime.getRuntime()
			.exec("pdflatex " + experimentPathFile.getAbsolutePath(),
				null,
				experimentPathFile.getParentFile()
			).waitFor();
		return new File(experimentPathFile.getParentFile().getAbsolutePath() + "/" + fileNameWithoutExtension + ".pdf");
	}
}
