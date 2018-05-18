package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class IBEAFactoryTest extends AbstractFactoriesTest<IBEAFactory> {

	@Override
	IBEAFactory createInstance() {
		return new IBEAFactory(new SomeDoubleProblem());
	}
}
