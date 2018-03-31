package pt.iscte.es2.algorithm_finder.factories.singleobjective;

import org.uma.jmetal.algorithm.singleobjective.particleswarmoptimization.StandardPSO2007;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

/**
 * @see <a href="http://clerc.maurice.free.fr/pso/SPSO_descriptions.pdf">http://clerc.maurice.free.fr/pso/SPSO_descriptions.pdf</a>
 */
//@BuilderTypes(solutionType = DoubleSolution.class)
// TODO
public class StandardPSO2007Factory implements AlgorithmBuilder<StandardPSO2007> {
	private final DoubleProblem problem;

	public StandardPSO2007Factory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public StandardPSO2007 build() {
		return new StandardPSO2007(
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
