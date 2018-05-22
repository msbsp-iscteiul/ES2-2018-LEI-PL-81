package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class ABYSSFactoryTest extends AbstractFactoriesTest<ABYSSFactory> {

	@Override
	ABYSSFactory createInstance() {
		return new ABYSSFactory(new SomeDoubleProblem());
	}
}
