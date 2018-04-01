package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.abyss.ABYSS;
import org.uma.jmetal.algorithm.multiobjective.abyss.ABYSSBuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.archive.impl.CrowdingDistanceArchive;
import org.uma.jmetal.util.archive.impl.HypervolumeArchive;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = ABYSS.class,
	solutionType = DoubleSolution.class
)
public class ABYSSFactory implements AlgorithmBuilder<ABYSS> {
	private final DoubleProblem problem;

	public ABYSSFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public ABYSS build() {
		return new ABYSSBuilder(
			problem,
			new CrowdingDistanceArchive<>(problem.getNumberOfVariables())
		).build();
	}
}
