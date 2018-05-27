package pt.iscte.es2.client_jar_loader;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.io.File;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class LoadClientJarProblemTest {
	@Test
	void loadsClientJar() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
		final Problem<Solution<?>> solutionProblem = new LoadClientJarProblem().loadProblemFromJar("src/test/resources/data/containee-1.0-SNAPSHOT-integer_2.jar");
		assertEquals("Problema_giro", solutionProblem.getName());
	}
}
