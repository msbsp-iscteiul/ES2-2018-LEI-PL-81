package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class NSGA3BinarySolutionFactoryTest extends AbstractFactoriesTest<NSGA3BinarySolutionFactory> {

	@Override
	NSGA3BinarySolutionFactory createInstance() {
		return new NSGA3BinarySolutionFactory(new SomeBinaryProblem());
	}
}
