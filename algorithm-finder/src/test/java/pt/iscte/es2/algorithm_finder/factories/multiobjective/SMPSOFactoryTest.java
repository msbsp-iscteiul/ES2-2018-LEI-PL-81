package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class SMPSOFactoryTest extends AbstractFactoriesTest<SMPSOFactory> {

	@Override
	SMPSOFactory createInstance() {
		return new SMPSOFactory(new SomeDoubleProblem());
	}
}
