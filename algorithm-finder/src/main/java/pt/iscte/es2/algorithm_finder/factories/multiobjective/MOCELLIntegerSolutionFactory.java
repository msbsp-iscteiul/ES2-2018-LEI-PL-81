package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mocell.MOCell;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = MOCell.class,
	solutionType = IntegerSolution.class
)
public class MOCELLIntegerSolutionFactory implements AlgorithmBuilder<MOCell<IntegerSolution>> {

	private final Problem<IntegerSolution> problem;

	public MOCELLIntegerSolutionFactory(Problem<IntegerSolution> problem) {
		this.problem = problem;
	}

	@Override
	public MOCell<IntegerSolution> build() {
		return new MOCellBuilder<>(
			problem,
			new IntegerSBXCrossover(0.9, 20),
			new IntegerPolynomialMutation(1.0 / problem.getNumberOfVariables(), 20)
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
