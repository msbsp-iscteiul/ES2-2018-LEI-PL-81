package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class DMOPSOFactoryTest extends AbstractFactoriesTest<DMOPSOFactory> {

	@Override
	DMOPSOFactory createInstance() {
		return new DMOPSOFactory(new SomeDoubleProblem());
	}
}
