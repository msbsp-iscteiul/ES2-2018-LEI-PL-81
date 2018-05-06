package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOA;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SMSEMOA.class,
	solutionType = IntegerSolution.class
)
public class SMSEMOAIntegerSolutionFactory implements AlgorithmBuilder<SMSEMOA<IntegerSolution>> {
	private final Problem<IntegerSolution> problem;

	public SMSEMOAIntegerSolutionFactory(Problem<IntegerSolution> problem) {
		this.problem = problem;
	}

	@Override
	public SMSEMOA<IntegerSolution> build() {
		return new SMSEMOABuilder<>(
			problem,
			new IntegerSBXCrossover(0.9, 20),
			new IntegerPolynomialMutation(1 / problem.getNumberOfVariables(), 20)
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
