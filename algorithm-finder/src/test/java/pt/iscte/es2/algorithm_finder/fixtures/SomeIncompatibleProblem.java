package pt.iscte.es2.algorithm_finder.fixtures;

import org.uma.jmetal.problem.Problem;

public class SomeIncompatibleProblem implements Problem {
	@Override
	public int getNumberOfVariables() {
		return 0;
	}

	@Override
	public int getNumberOfObjectives() {
		return 0;
	}

	@Override
	public int getNumberOfConstraints() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void evaluate(Object solution) {

	}

	@Override
	public Object createSolution() {
		return null;
	}
}
