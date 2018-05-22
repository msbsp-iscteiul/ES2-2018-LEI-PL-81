package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeBinaryProblem;

class SMSEMOABinarySolutionFactoryTest extends AbstractFactoriesTest<SMSEMOABinarySolutionFactory> {

	@Override
	SMSEMOABinarySolutionFactory createInstance() {
		return new SMSEMOABinarySolutionFactory(new SomeBinaryProblem());
	}
}
