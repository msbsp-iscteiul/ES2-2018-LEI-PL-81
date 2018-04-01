package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSO;
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSOBuilder;
import org.uma.jmetal.operator.impl.mutation.NonUniformMutation;
import org.uma.jmetal.operator.impl.mutation.UniformMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;
import org.uma.jmetal.util.pseudorandom.RandomGenerator;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = OMOPSO.class,
	solutionType = DoubleSolution.class
)
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
		)
			.setNonUniformMutation(new NonUniformMutation(.5, .5, 10))
			.setUniformMutation(new UniformMutation(.5, .5, Math::random))
			.build();
	}
}
