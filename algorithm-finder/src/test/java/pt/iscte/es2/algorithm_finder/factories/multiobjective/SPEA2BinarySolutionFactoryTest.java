package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class SPEA2BinarySolutionFactoryTest extends AbstractFactoriesTest<SPEA2BinarySolutionFactory> {

	@Override
	SPEA2BinarySolutionFactory createInstance() {
		return new SPEA2BinarySolutionFactory(new SomeBinaryProblem());
	}
}
