package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHC;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(solutionType = BinarySolution.class)
public class MOCHCFactory implements AlgorithmBuilder<MOCHC> {
	private final BinaryProblem problem;

	public MOCHCFactory(BinaryProblem problem) {
		this.problem = problem;
	}

	@Override
	public MOCHC build() {
		return new MOCHCBuilder(problem).build();
	}
}
