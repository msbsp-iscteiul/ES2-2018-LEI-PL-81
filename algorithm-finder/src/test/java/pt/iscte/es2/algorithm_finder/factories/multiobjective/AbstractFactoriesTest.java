package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;
import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractFactoriesTest<T extends AlgorithmBuilder> {

	private T instance;

	abstract T createInstance();

	@BeforeEach
	void setUp() {
		instance = createInstance();
	}

	@Test
	void itInstantiatesTheAlgorithm() {
		final Algorithm algorithm = instance.build();
		final Class<? extends Algorithm> algorithmType = algorithm.getClass();
		assertTrue(instance.getClass().getAnnotation(BuilderTypes.class).algorithm().isAssignableFrom(algorithmType));
	}
}
