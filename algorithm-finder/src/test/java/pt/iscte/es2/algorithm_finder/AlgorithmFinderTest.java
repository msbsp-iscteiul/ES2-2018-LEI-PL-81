package pt.iscte.es2.algorithm_finder;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.IntegerSolution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class AlgorithmFinderTest {

	@Test
	void findsAlgorithmsCompatibleWithProblemBinaryTypeAndSolution() {
		final SomeBinaryProblem binaryProblem = new SomeBinaryProblem();
		final List<Constructor<?>> algorithmBuilderConstructors =
			new AlgorithmFinder(binaryProblem).execute().getConstructors();
		System.out.println(algorithmBuilderConstructors);
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
		System.out.println(algorithmBuilderConstructors);
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

	private class SomeIntegerProblem extends AbstractIntegerProblem {

		@Override
		public void evaluate(IntegerSolution solution) {

		}
	}

	private class SomeBinaryProblem extends AbstractBinaryProblem {

		@Override
		protected int getBitsPerVariable(int index) {
			return 0;
		}

		@Override
		public void evaluate(BinarySolution solution) {

		}
	}
}
