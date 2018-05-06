package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = NSGAII.class,
	solutionType = IntegerSolution.class
)
public class NSGA2IntegerSolutionFactory implements AlgorithmBuilder<NSGAII<IntegerSolution>> {
	private final Problem<IntegerSolution> problem;

	public NSGA2IntegerSolutionFactory(Problem<IntegerSolution> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAII<IntegerSolution> build() {
		return new NSGAIIBuilder<>(
			problem,
			new IntegerSBXCrossover(0.9, 20.0),
			new IntegerPolynomialMutation(1 / problem.getNumberOfVariables(), 20.0)
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
