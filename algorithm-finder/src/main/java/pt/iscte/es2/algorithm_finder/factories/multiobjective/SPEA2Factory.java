package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class SPEA2Factory<S extends Solution<?>> implements AlgorithmBuilder<SPEA2<S>> {
	public SPEA2Factory() {
		super();
	}

	@Override
	public SPEA2<S> build() {
		return null;
//		return new SPEA2Builder<>();
	}
}
