package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class NSGA2BinarySolutionFactoryTest extends AbstractFactoriesTest<NSGA2BinarySolutionFactory> {

	@Override
	NSGA2BinarySolutionFactory createInstance() {
		return new NSGA2BinarySolutionFactory(new SomeBinaryProblem());
	}
}
