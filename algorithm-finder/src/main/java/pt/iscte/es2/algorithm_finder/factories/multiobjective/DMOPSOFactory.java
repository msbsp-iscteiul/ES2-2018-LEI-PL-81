package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSO;
import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSOBuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = DMOPSO.class,
	solutionType = DoubleSolution.class
)
public class DMOPSOFactory implements AlgorithmBuilder<DMOPSO> {
	private final DoubleProblem problem;

	public DMOPSOFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public DMOPSO build() {
		return new DMOPSOBuilder(problem)
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS / 100)
//			.setSwarmSize(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
