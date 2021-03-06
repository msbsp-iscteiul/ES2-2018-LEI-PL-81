package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import pt.iscte.es2.algorithm_finder.fixtures.SomeIntegerProblem;

import static org.junit.jupiter.api.Assertions.*;

class SMSEMOAIntegerSolutionFactoryTest extends AbstractFactoriesTest<SMSEMOAIntegerSolutionFactory> {

	@Override
	SMSEMOAIntegerSolutionFactory createInstance() {
		return new SMSEMOAIntegerSolutionFactory(new SomeIntegerProblem());
	}
}
