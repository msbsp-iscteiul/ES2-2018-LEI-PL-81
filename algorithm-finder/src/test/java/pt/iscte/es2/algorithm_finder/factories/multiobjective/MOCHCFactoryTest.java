package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class MOCHCFactoryTest extends AbstractFactoriesTest<MOCHCFactory> {

	@Override
	MOCHCFactory createInstance() {
		return new MOCHCFactory(new SomeBinaryProblem());
	}
}
