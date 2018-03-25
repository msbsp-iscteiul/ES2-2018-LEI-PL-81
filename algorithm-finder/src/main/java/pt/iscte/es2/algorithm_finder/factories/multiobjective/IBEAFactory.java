package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.ibea.IBEA;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(solutionType = DoubleSolution.class)
public class IBEAFactory implements AlgorithmBuilder<IBEA<DoubleSolution>> {
	private final DoubleProblem problem;

	public IBEAFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public IBEA<DoubleSolution> build() {
		return new IBEABuilder(problem).build();
	}
}
