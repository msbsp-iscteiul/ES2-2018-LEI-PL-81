package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class NSGAIIFactory<S extends Solution<?>> implements AlgorithmBuilder<NSGAII<S>> {
	private final Problem<S> problem;

	public NSGAIIFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAII<S> build() {
		return null;
//		return new NSGAIIBuilder<>(problem).build();
	}
}
