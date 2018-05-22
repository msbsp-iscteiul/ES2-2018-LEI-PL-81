package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class MOCELLBinarySolutionFactoryTest extends AbstractFactoriesTest<MOCELLBinarySolutionFactory> {

	@Override
	MOCELLBinarySolutionFactory createInstance() {
		return new MOCELLBinarySolutionFactory(new SomeBinaryProblem());
	}
}
