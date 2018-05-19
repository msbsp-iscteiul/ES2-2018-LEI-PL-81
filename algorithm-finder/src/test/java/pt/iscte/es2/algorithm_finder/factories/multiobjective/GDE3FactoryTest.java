package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class GDE3FactoryTest extends AbstractFactoriesTest<GDE3Factory> {

	@Override
	GDE3Factory createInstance() {
		return new GDE3Factory(new SomeDoubleProblem());
	}
}
