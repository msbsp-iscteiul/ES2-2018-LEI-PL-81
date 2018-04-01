package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHC;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.util.EnvironmentalSelection;
import org.uma.jmetal.operator.impl.selection.BestSolutionSelection;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.comparator.DominanceComparator;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

//@BuilderTypes(solutionType = BinarySolution.class)
//TODO
public class MOCHCFactory implements AlgorithmBuilder<MOCHC> {
	private final BinaryProblem problem;

	public MOCHCFactory(BinaryProblem problem) {
		this.problem = problem;
	}

	@Override
	public MOCHC build() {
		return new MOCHCBuilder(problem)
			.setParentSelection(new BestSolutionSelection<>(new DominanceComparator<>()))
			.setNewGenerationSelection(new BestSolutionSelection(new DominanceComparator()))
			.build();
	}
}
