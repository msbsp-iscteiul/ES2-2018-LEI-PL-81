package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class RandomSearchFactoryTest extends AbstractFactoriesTest<RandomSearchFactory> {

	@Override
	RandomSearchFactory createInstance() {
		return new RandomSearchFactory<>(new SomeBinaryProblem());
	}
}
