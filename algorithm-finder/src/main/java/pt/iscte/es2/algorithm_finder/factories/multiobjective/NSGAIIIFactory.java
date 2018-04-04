package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIII;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

// TODO
//@BuilderTypes(solutionType = Solution.class)
public class NSGAIIIFactory<S extends Solution<?>> implements AlgorithmBuilder<NSGAIII<S>> {

	private final Problem<S> problem;

	public NSGAIIIFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public NSGAIII<S> build() {
		return new NSGAIIIBuilder<>(problem).build();
	}
}
