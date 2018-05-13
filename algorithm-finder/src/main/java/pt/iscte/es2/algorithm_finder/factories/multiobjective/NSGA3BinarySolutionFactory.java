package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = NSGAIII.class,
	solutionType = BinarySolution.class
)
public class NSGA3BinarySolutionFactory implements AlgorithmBuilder<NSGAIII<BinarySolution>> {

	private final Problem<BinarySolution> problem;

	public NSGA3BinarySolutionFactory(Problem<BinarySolution> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAIII<BinarySolution> build() {
		return new NSGAIIIBuilder<>(problem)
			.setCrossoverOperator(new HUXCrossover(1.0))
			.setMutationOperator(new BitFlipMutation(1.0 / problem.getNumberOfVariables()))
			.setSelectionOperator(new RandomSelection<>())
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS / 10)
			.build();
	}
}
