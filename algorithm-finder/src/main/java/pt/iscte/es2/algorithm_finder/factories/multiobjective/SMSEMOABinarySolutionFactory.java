package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOA;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SMSEMOA.class,
	solutionType = BinarySolution.class
)
public class SMSEMOABinarySolutionFactory implements AlgorithmBuilder<SMSEMOA<BinarySolution>> {
	private final Problem<BinarySolution> problem;

	public SMSEMOABinarySolutionFactory(Problem<BinarySolution> problem) {
		this.problem = problem;
	}

	@Override
	public SMSEMOA<BinarySolution> build() {
		return new SMSEMOABuilder<>(
			problem,
			new SinglePointCrossover(1.0),
			new BitFlipMutation(1.0 / problem.getNumberOfVariables())
		)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
