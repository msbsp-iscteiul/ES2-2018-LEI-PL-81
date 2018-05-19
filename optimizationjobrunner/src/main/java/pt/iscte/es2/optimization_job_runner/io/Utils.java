package pt.iscte.es2.optimization_job_runner.io;

import java.io.File;

/**
 * IO utilities
 */
public class Utils {

	/**
	 * Delete directory
	 * @param file directory to delete
	 */
	public static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}
}
