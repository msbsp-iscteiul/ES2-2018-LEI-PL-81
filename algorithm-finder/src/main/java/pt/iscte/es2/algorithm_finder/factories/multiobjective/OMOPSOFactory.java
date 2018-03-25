package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSO;
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSOBuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;

public class OMOPSOFactory implements AlgorithmBuilder<OMOPSO> {
	private final DoubleProblem problem;

	public OMOPSOFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public OMOPSO build() {
		return new OMOPSOBuilder(
			problem,
			new MultithreadedSolutionListEvaluator<>(
				Runtime.getRuntime().availableProcessors(),
				problem
			)
		).build();
	}
}
