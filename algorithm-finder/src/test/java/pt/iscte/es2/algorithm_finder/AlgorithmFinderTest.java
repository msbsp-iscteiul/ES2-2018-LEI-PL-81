package pt.iscte.es2.algorithm_finder;

import org.junit.jupiter.api.Test;
import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;
import pt.iscte.es2.algorithm_finder.fixtures.SomeIncompatibleProblem;
import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class AlgorithmFinderTest {

	@Test
	void findsAlgorithmsCompatibleWithProblemBinaryTypeAndSolution() {
		final SomeBinaryProblem binaryProblem = new SomeBinaryProblem();
		final List<Constructor<?>> algorithmBuilderConstructors =
			new AlgorithmFinder(binaryProblem).execute().getConstructors();
		algorithmBuilderConstructors.forEach(constructor -> {
			try {
				assertNotNull(constructor.newInstance(binaryProblem));
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	void findsAlgorithmsCompatibleWithProblemIntegerTypeAndSolution() {
		final SomeIntegerProblem integerProblem = new SomeIntegerProblem();
		final List<Constructor<?>> algorithmBuilderConstructors =
			new AlgorithmFinder(integerProblem).execute().getConstructors();
		algorithmBuilderConstructors.forEach(constructor -> {
			try {
				assertNotNull(constructor.newInstance(integerProblem));
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	void findsSolutionTypeName() {
		final SomeBinaryProblem problem = new SomeBinaryProblem();
		final String solutionTypeName = new AlgorithmFinder(problem).execute().getSolutionTypeName();
		assertEquals("org.uma.jmetal.solution.BinarySolution", solutionTypeName);
	}

	@Test
	void findsConstructorsByAlgorithmName() {
		final SomeBinaryProblem problem = new SomeBinaryProblem();
		final AlgorithmFinder.AlgorithmFinderResult result = new AlgorithmFinder(problem).execute();
		final List<Constructor<?>> constructorsForAlgorithms = result
			.getConstructorsForAlgorithms(Arrays.asList(
				"org.uma.jmetal.algorithm.multiobjective.mocell.MOCell",
				"org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII"
			));
		assertEquals(2, constructorsForAlgorithms.size());
	}

	@Test
	void returnsEmptyWhenNoCompatibleMatches() {
		final SomeIncompatibleProblem incompatibleProblem = new SomeIncompatibleProblem();
		final AlgorithmFinder.AlgorithmFinderResult result = new AlgorithmFinder(incompatibleProblem).execute();
		assertEquals(0, result.getAlgorithms().size());
	}
}
