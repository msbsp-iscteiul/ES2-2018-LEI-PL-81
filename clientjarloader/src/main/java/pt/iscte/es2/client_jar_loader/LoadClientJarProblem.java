package pt.iscte.es2.client_jar_loader;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
		URLClassLoader child = new URLClassLoader(new URL[]{new URL(file)}, this.getClass().getClassLoader());
		final Class<? extends Problem<Solution<?>>> pluginClass =
			(Class<? extends Problem<Solution<?>>>) child.loadClass("pt.iscte.es2.Plugin");
		return pluginClass.newInstance();
	}
}
