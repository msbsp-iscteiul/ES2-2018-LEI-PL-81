package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.paes.PAES;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.mutation.NonUniformMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = PAES.class,
	solutionType = BinarySolution.class
)
public class PAESBinarySolutionFactory implements AlgorithmBuilder<PAES<BinarySolution>> {
	private final Problem<BinarySolution> problem;

	public PAESBinarySolutionFactory(Problem<BinarySolution> problem) {
		this.problem = problem;
	}

	@Override
	public PAES<BinarySolution> build() {
		return new PAESBuilder<>(problem)
			.setArchiveSize(100)
			.setBiSections(2)
			.setMutationOperator(new BitFlipMutation(1.0 / problem.getNumberOfVariables()))
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
