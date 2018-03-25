package pt.iscte.es2.algorithm_finder.factories.singleobjective.evolutionstrategy;

import org.uma.jmetal.algorithm.singleobjective.evolutionstrategy.NonElitistEvolutionStrategy;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class NonElitistEvolutionStrategyFactory<S extends Solution<?>> implements AlgorithmBuilder<NonElitistEvolutionStrategy<S>> {
	@Override
	public NonElitistEvolutionStrategy<S> build() {
		return null;
		// TODO
	}
}
