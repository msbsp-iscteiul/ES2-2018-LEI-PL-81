package pt.iscte.es2.algorithm_finder.fixtures;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class SomeBinaryProblem extends AbstractBinaryProblem {

	@Override
	public int getNumberOfObjectives() {
		return 10;
	}

	@Override
	protected int getBitsPerVariable(int index) {
		return 10;
	}

	@Override
	public void evaluate(BinarySolution solution) {

	}
}
