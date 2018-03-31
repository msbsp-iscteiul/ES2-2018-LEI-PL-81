package pt.iscte.es2.algorithm_finder;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlgorithmFinderTest {

	@Test
	void findsAlgorithmsCompatibleWithProblemTypeAndSolution() {
		final SomeBinaryProblem problem = new SomeBinaryProblem();
                final List<Constructor<?>> algorithmBuilderConstructors = new AlgorithmFinder(problem).execute();
                algorithmBuilderConstructors.forEach(constructor -> {
			try {
				assertNotNull(constructor.newInstance(problem));
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
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
