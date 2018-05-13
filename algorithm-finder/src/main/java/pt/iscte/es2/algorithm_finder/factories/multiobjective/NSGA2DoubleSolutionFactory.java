package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = NSGAII.class,
	solutionType = DoubleSolution.class
)
public class NSGA2DoubleSolutionFactory implements AlgorithmBuilder<NSGAII<DoubleSolution>> {
	private final DoubleProblem problem;

	public NSGA2DoubleSolutionFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public NSGAII<DoubleSolution> build() {
		return new NSGAIIBuilder<>(
			problem,
			new SBXCrossover(.5, 5),
			new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 10.0)
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
