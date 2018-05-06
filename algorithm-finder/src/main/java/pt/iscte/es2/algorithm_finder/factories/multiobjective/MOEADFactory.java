package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.moead.AbstractMOEAD;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = AbstractMOEAD.class,
	solutionType = DoubleSolution.class
)
public class MOEADFactory implements AlgorithmBuilder<AbstractMOEAD<DoubleSolution>> {
	private final Problem<DoubleSolution> problem;

	public MOEADFactory(Problem<DoubleSolution> problem) {
		this.problem = problem;
	}

	@Override
	public AbstractMOEAD<DoubleSolution> build() {
		return new MOEADBuilder(problem, MOEADBuilder.Variant.MOEAD)
			.setMaxEvaluations(AlgorithmConstants.MAX_EVALUTIONS)
			.build();
	}
}
