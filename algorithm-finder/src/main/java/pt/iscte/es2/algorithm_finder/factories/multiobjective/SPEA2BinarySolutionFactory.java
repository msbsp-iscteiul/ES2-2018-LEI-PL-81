package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SPEA2.class,
	solutionType = BinarySolution.class
)
public class SPEA2BinarySolutionFactory implements AlgorithmBuilder<SPEA2<BinarySolution>> {
	private final Problem<BinarySolution> problem;

	public SPEA2BinarySolutionFactory(Problem<BinarySolution> problem) {
		this.problem = problem;
	}

	@Override
	public SPEA2<BinarySolution> build() {
		return new SPEA2Builder<>(
			problem,
			new SinglePointCrossover(1.0),
			new BitFlipMutation(1.0 / problem.getNumberOfVariables())
		)
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
