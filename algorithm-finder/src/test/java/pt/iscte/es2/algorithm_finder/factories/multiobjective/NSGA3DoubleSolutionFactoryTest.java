package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class NSGA3DoubleSolutionFactoryTest extends AbstractFactoriesTest<NSGA3DoubleSolutionFactory> {

	@Override
	NSGA3DoubleSolutionFactory createInstance() {
		return new NSGA3DoubleSolutionFactory(new SomeDoubleProblem());
	}
}
