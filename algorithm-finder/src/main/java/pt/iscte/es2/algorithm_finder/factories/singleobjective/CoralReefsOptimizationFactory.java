package pt.iscte.es2.algorithm_finder.factories.singleobjective;

import org.uma.jmetal.algorithm.singleobjective.coralreefsoptimization.CoralReefsOptimization;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class CoralReefsOptimizationFactory<S extends Solution<?>>
	implements AlgorithmBuilder<CoralReefsOptimization<S>> {

	@Override
	public CoralReefsOptimization<S> build() {
		return null;
//		return new CoralReefsOptimizationBuilder<>();
	}
}
