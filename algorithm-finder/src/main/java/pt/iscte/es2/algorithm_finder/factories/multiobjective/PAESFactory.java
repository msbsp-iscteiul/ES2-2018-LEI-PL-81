package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.paes.PAES;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(solutionType = Solution.class)
public class PAESFactory<S extends Solution<?>> implements AlgorithmBuilder<PAES<S>> {
	private final Problem<S> problem;

	public PAESFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public PAES<S> build() {
		return new PAESBuilder<>(problem).build();
	}
}
