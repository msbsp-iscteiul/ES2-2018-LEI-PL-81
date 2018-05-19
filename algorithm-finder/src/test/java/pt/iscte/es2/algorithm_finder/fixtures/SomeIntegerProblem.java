package pt.iscte.es2.algorithm_finder.fixtures;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class SomeIntegerProblem extends AbstractIntegerProblem {

	@Override
	public int getNumberOfObjectives() {
		return 10;
	}

	@Override
	public int getNumberOfVariables() {
		return 10;
	}

	@Override
	public void evaluate(IntegerSolution solution) {

	}
}
