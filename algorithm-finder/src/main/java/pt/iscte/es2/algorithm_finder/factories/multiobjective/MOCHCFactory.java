package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHC;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.BestSolutionSelection;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.comparator.DominanceComparator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = MOCHC.class,
	solutionType = BinarySolution.class
)
public class MOCHCFactory implements AlgorithmBuilder<MOCHC> {
	private final BinaryProblem problem;

	public MOCHCFactory(BinaryProblem problem) {
		this.problem = problem;
	}

	@Override
	public MOCHC build() {
		return new MOCHCBuilder(problem)
			.setCrossover(new HUXCrossover(1.0))
			.setNewGenerationSelection(new RankingAndCrowdingSelection<>(100))
			.setCataclysmicMutation(new BitFlipMutation(0.35))
			.setParentSelection(new RandomSelection<>())
			.setEvaluator(new SequentialSolutionListEvaluator<>())
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
