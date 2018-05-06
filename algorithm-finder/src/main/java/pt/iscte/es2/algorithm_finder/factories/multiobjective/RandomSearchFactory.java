package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearch;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = RandomSearch.class,
	solutionType = Solution.class
)
public class RandomSearchFactory<S extends Solution<?>> implements AlgorithmBuilder<RandomSearch<S>> {
	private final Problem<S> problem;

	public RandomSearchFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public RandomSearch<S> build() {
		return new RandomSearchBuilder<>(problem)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
