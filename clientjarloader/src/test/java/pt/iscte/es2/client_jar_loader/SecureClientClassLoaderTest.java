package pt.iscte.es2.client_jar_loader;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.Problem;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class SecureClientClassLoaderTest {
	@Test
	void loadsClientJar() throws Exception {
		final File jar = new File("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
		final String file = "file://" + jar.getAbsolutePath();
		final SecureClientClassLoader loader = new SecureClientClassLoader(new URL(file));
		final Object p = loader.loadClass("pt.iscte.es2.Plugin").newInstance();
		assertTrue(p instanceof Problem);
	}
}
