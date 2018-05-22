package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class MOCELLDoubleSolutionFactoryTest extends AbstractFactoriesTest<MOCELLDoubleSolutionFactory> {

	@Override
	MOCELLDoubleSolutionFactory createInstance() {
		return new MOCELLDoubleSolutionFactory(new SomeDoubleProblem());
	}
}
