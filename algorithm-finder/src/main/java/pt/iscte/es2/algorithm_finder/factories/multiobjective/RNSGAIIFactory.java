package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class RNSGAIIFactory<S extends Solution<?>> implements AlgorithmBuilder<RNSGAII<S>> {
	private final Problem<S> problem;

	public RNSGAIIFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public RNSGAII<S> build() {
		return null;
//		return new RNSGAIIBuilder<>(problem);
	}
}
