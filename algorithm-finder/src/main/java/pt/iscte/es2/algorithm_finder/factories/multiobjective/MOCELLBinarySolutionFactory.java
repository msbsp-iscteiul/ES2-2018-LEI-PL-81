package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mocell.MOCell;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = MOCell.class,
	solutionType = BinarySolution.class
)
public class MOCELLBinarySolutionFactory implements AlgorithmBuilder<MOCell<BinarySolution>> {

	private final Problem<BinarySolution> problem;

	public MOCELLBinarySolutionFactory(Problem<BinarySolution> problem) {
		this.problem = problem;
	}

	@Override
	public MOCell<BinarySolution> build() {
		return new MOCellBuilder<>(
			problem,
			new SinglePointCrossover(1.0),
			new BitFlipMutation(1.0 / problem.getNumberOfVariables())
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
