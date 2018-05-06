package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = NSGAIII.class,
	solutionType = IntegerSolution.class
)
public class NSGA3IntegerSolutionFactory implements AlgorithmBuilder<NSGAIII<IntegerSolution>> {

	private final Problem<IntegerSolution> problem;

	public NSGA3IntegerSolutionFactory(Problem<IntegerSolution> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAIII<IntegerSolution> build() {
		return new NSGAIIIBuilder<>(problem)
			.setCrossoverOperator(new IntegerSBXCrossover(0.9, 20.0))
			.setMutationOperator(new IntegerPolynomialMutation(1 / problem.getNumberOfVariables(), 20.0))
			.setSelectionOperator(new RandomSelection<>())
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
