package pt.iscte.es2.algorithm_finder.factories.singleobjective;

import org.uma.jmetal.algorithm.singleobjective.particleswarmoptimization.StandardPSO2011;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

/**
 * @see <a href="http://clerc.maurice.free.fr/pso/SPSO_descriptions.pdf">http://clerc.maurice.free.fr/pso/SPSO_descriptions.pdf</a>
 */
@BuilderTypes(solutionType = DoubleSolution.class)
public class StandardPSO2011Factory implements AlgorithmBuilder<StandardPSO2011> {
	private final DoubleProblem problem;

	public StandardPSO2011Factory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public StandardPSO2011 build() {
		return new StandardPSO2011(
			problem,
			0,
			40,
			10,
			30,
			new MultithreadedSolutionListEvaluator<>(
				Runtime.getRuntime().availableProcessors(),
				problem
			)
		);
	}
}
