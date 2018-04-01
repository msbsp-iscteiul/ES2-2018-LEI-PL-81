package pt.iscte.es2.client_jar_loader;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadClientJarProblem {
	public Problem<Solution<?>> loadProblemFromJar(String jarFilePath)
		throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		final File jar = new File(jarFilePath);
		final String file = "file://" + jar.getAbsolutePath();
		final ClassLoader clientClassLoader = new SecureClientClassLoader(new URL(file));
		final Class<? extends Problem<Solution<?>>> pluginClass =
			(Class<? extends Problem<Solution<?>>>) clientClassLoader.loadClass("Plugin");
		return pluginClass.newInstance();
	}
}
