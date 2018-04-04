package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.mocell.MOCell;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

// TODO generic builder
public class MOCELLFactory<S extends Solution<?>> implements AlgorithmBuilder<MOCell<S>> {

	private final Problem<S> problem;

	public MOCELLFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public MOCell<S> build() {
		return null;
//		return new MOCellBuilder<>(
//			problem,
//			new SBXCrossover()
//		).build();
	}
}
