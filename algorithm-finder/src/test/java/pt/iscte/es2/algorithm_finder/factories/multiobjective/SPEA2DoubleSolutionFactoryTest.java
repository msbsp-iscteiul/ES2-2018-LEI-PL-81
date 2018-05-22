package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class SPEA2DoubleSolutionFactoryTest extends AbstractFactoriesTest<SPEA2DoubleSolutionFactory> {

	@Override
	SPEA2DoubleSolutionFactory createInstance() {
		return new SPEA2DoubleSolutionFactory(new SomeDoubleProblem());
	}
}
