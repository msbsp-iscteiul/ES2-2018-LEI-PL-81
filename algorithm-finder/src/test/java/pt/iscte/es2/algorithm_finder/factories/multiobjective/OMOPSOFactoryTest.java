package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class OMOPSOFactoryTest extends AbstractFactoriesTest<OMOPSOFactory> {

	@Override
	OMOPSOFactory createInstance() {
		return new OMOPSOFactory(new SomeDoubleProblem());
	}
}
