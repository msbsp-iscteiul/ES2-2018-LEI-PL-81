package pt.iscte.es2.algorithm_finder.factories.singleobjective.evolutionstrategy;

import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.ElitistEvolutionStrategy;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class ElitistEvolutionStrategyFactory<S extends Solution<?>> implements AlgorithmBuilder<ElitistEvolutionStrategy<S>> {

	private final Problem<S> problem;

	public ElitistEvolutionStrategyFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public ElitistEvolutionStrategy<S> build() {
		return null;
//		return new EvolutionStrategyBuilder<S>(
//			problem,
//			new PolynomialMutation(
//				1.0 / problem.getNumberOfVariables(), 10.0
//			),
//			EvolutionStrategyBuilder.EvolutionStrategyVariant.ELITIST
//		).build();
	}
}
