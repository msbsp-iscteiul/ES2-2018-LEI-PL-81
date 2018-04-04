package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class PESA2Factory<S extends Solution<?>> implements AlgorithmBuilder<PESA2<S>> {
	private final Problem<S> problem;

	public PESA2Factory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public PESA2<S> build() {
		return null;
//		return new PESA2Builder<>().build();
	}
}
