package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import static org.junit.jupiter.api.Assertions.*;

class NSGA2IntegerSolutionFactoryTest extends AbstractFactoriesTest<NSGA2IntegerSolutionFactory> {

	@Override
	NSGA2IntegerSolutionFactory createInstance() {
		return new NSGA2IntegerSolutionFactory(new SomeIntegerProblem());
	}
}
