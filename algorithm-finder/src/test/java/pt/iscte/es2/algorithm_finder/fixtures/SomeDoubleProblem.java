package pt.iscte.es2.algorithm_finder.fixtures;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class SomeDoubleProblem extends AbstractDoubleProblem {

	@Override
	public int getNumberOfObjectives() {
		return 10;
	}

	@Override
	public void evaluate(DoubleSolution solution) {

	}
}
