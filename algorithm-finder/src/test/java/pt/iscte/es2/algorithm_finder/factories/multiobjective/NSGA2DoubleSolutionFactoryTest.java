package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class NSGA2DoubleSolutionFactoryTest extends AbstractFactoriesTest<NSGA2DoubleSolutionFactory> {

	@Override
	NSGA2DoubleSolutionFactory createInstance() {
		return new NSGA2DoubleSolutionFactory(new SomeDoubleProblem());
	}
}
