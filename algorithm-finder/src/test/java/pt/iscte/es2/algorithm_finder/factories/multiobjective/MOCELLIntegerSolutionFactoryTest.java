package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import static org.junit.jupiter.api.Assertions.*;

class MOCELLIntegerSolutionFactoryTest extends AbstractFactoriesTest<MOCELLIntegerSolutionFactory> {

	@Override
	MOCELLIntegerSolutionFactory createInstance() {
		return new MOCELLIntegerSolutionFactory(new SomeIntegerProblem());
	}
}
