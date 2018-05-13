package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SPEA2.class,
	solutionType = IntegerSolution.class
)
public class SPEA2IntegerSolutionFactory implements AlgorithmBuilder<SPEA2<IntegerSolution>> {
	private final Problem<IntegerSolution> problem;

	public SPEA2IntegerSolutionFactory(Problem<IntegerSolution> problem) {
		this.problem = problem;
	}

	@Override
	public SPEA2<IntegerSolution> build() {
		return new SPEA2Builder<>(
			problem,
			new IntegerSBXCrossover(0.9, 20),
			new IntegerPolynomialMutation(1 / problem.getNumberOfVariables(), 20)
		)
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS / 100)
			.build();
	}
}
