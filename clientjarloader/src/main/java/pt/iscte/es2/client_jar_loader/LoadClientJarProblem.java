package pt.iscte.es2.client_jar_loader;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Client problem jar loader
 */
public class LoadClientJarProblem {
	/**
	 * Load the problem from the given jar
	 * @param jarFilePath the client jar path
	 * @return the client problem
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Problem<Solution<?>> loadProblemFromJar(String jarFilePath)
		throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		final File jar = new File(jarFilePath);
		final String file = "file://" + jar.getAbsolutePath();
		final SecureClientClassLoader clientClassLoader = new SecureClientClassLoader(new URL(file));
		final Class<? extends Problem<Solution<?>>> pluginClass =
			(Class<? extends Problem<Solution<?>>>) clientClassLoader.loadClass("pt.iscte.es2.Plugin");
		return pluginClass.newInstance();
	}
}
