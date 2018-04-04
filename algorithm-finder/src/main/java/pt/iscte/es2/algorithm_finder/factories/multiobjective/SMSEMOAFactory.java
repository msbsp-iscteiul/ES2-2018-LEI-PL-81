package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOA;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.AlgorithmBuilder;

// TODO generic builder
public class SMSEMOAFactory<S extends Solution<?>> implements AlgorithmBuilder<SMSEMOA<S>> {
	private final Problem<S> problem;

	public SMSEMOAFactory(Problem<S> problem) {
		this.problem = problem;
	}

	@Override
	public SMSEMOA<S> build() {
		return null;
//		return new SMSEMOABuilder<>(problem);
	}
}
