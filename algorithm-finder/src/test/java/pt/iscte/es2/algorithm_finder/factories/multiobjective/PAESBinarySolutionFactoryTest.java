package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class PAESBinarySolutionFactoryTest extends AbstractFactoriesTest<PAESBinarySolutionFactory> {

	@Override
	PAESBinarySolutionFactory createInstance() {
		return new PAESBinarySolutionFactory(new SomeBinaryProblem());
	}
}
