package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class MOEADFactoryTest extends AbstractFactoriesTest<MOEADFactory> {

	@Override
	MOEADFactory createInstance() {
		return new MOEADFactory(new SomeDoubleProblem());
	}
}
