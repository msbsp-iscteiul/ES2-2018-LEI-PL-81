package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = GDE3.class,
	solutionType = DoubleSolution.class
)
public class GDE3Factory implements AlgorithmBuilder<GDE3> {
	private final DoubleProblem problem;

	public GDE3Factory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public GDE3 build() {
		return new GDE3Builder(problem).build();
	}
}
