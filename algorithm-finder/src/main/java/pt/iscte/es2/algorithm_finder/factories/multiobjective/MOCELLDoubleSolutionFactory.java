package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mocell.MOCell;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = MOCell.class,
	solutionType = DoubleSolution.class
)
public class MOCELLDoubleSolutionFactory implements AlgorithmBuilder<MOCell<DoubleSolution>> {

	private final Problem<DoubleSolution> problem;

	public MOCELLDoubleSolutionFactory(Problem<DoubleSolution> problem) {
		this.problem = problem;
	}

	@Override
	public MOCell<DoubleSolution> build() {
		return new MOCellBuilder<>(
			problem,
			new SBXCrossover(1.0, 5),
			new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 10)
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
