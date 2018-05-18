package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import static org.junit.jupiter.api.Assertions.*;

class NSGA3IntegerSolutionFactoryTest extends AbstractFactoriesTest<NSGA3IntegerSolutionFactory> {

	@Override
	NSGA3IntegerSolutionFactory createInstance() {
		return new NSGA3IntegerSolutionFactory(new SomeIntegerProblem());
	}
}
