package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import static org.junit.jupiter.api.Assertions.*;

class SPEA2IntegerSolutionFactoryTest extends AbstractFactoriesTest<SPEA2IntegerSolutionFactory> {

	@Override
	SPEA2IntegerSolutionFactory createInstance() {
		return new SPEA2IntegerSolutionFactory(new SomeIntegerProblem());
	}
}
