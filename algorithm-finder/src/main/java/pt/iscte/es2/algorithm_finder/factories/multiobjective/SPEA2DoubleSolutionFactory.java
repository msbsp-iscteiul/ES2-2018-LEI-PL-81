package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SPEA2.class,
	solutionType = DoubleSolution.class
)
public class SPEA2DoubleSolutionFactory implements AlgorithmBuilder<SPEA2<DoubleSolution>> {
	private final Problem<DoubleSolution> problem;

	public SPEA2DoubleSolutionFactory(Problem<DoubleSolution> problem) {
		this.problem = problem;
	}

	@Override
	public SPEA2<DoubleSolution> build() {
		return new SPEA2Builder<>(
			problem,
			new SBXCrossover(.5, 5),
			new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 10.0)
		)
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
