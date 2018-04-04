package pt.iscte.es2.algorithm_finder.factories.singleobjective;

import org.uma.jmetal.algorithm.singleobjective.differentialevolution.DifferentialEvolution;
import org.uma.jmetal.algorithm.singleobjective.differentialevolution.DifferentialEvolutionBuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

// TODO
//@BuilderTypes(solutionType = DoubleSolution.class)
public class DifferentialEvolutionFactory implements AlgorithmBuilder<DifferentialEvolution> {
	private final DoubleProblem problem;

	public DifferentialEvolutionFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public DifferentialEvolution build() {
		return new DifferentialEvolutionBuilder(problem).build();
	}
}
