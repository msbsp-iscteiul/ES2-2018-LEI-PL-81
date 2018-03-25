package pt.iscte.es2.algorithm_finder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmFinderTest {

	@Test
	void findsAlgorithmsCompatibleWithProblemTypeAndSolution() {
		final SomeBinaryProblem problem = new SomeBinaryProblem();
		final List<Class<?>> algorithmBuilders = new AlgorithmFinder(problem).execute();
		algorithmBuilders.forEach(builder -> {
			assertTrue(builder.isAnnotationPresent(BuilderTypes.class));
			assertTrue(
				builder
					.getAnnotation(BuilderTypes.class)
					.solutionType()
					.isAssignableFrom(BinarySolution.class)
			);
			final Optional<Constructor<?>> compatibleConstructor = Arrays.stream(builder.getConstructors())
				.filter(constructor -> {
					return constructor.getParameterTypes()[0].isAssignableFrom(problem.getClass());
				}).findAny();
			assertTrue(compatibleConstructor.isPresent());
			final Constructor<?> constructor = compatibleConstructor.get();
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
