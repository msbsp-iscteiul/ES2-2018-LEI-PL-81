package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = NSGAIII.class,
	solutionType = DoubleSolution.class
)
public class NSGA3DoubleSolutionFactory implements AlgorithmBuilder<NSGAIII<DoubleSolution>> {

	private final Problem<DoubleSolution> problem;

	public NSGA3DoubleSolutionFactory(Problem<DoubleSolution> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAIII<DoubleSolution> build() {
		return new NSGAIIIBuilder<>(problem)
			.setCrossoverOperator(new SBXCrossover(.5, 5))
			.setMutationOperator(new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 10.0))
			.setSelectionOperator(new RandomSelection<>())
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS / 10)
			.build();
	}
}
