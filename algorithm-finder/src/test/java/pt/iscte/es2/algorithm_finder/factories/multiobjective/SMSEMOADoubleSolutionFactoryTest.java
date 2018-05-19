package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeDoubleProblem;

import static org.junit.jupiter.api.Assertions.*;

class SMSEMOADoubleSolutionFactoryTest extends AbstractFactoriesTest<SMSEMOADoubleSolutionFactory> {

	@Override
	SMSEMOADoubleSolutionFactory createInstance() {
		return new SMSEMOADoubleSolutionFactory(new SomeDoubleProblem());
	}
}
