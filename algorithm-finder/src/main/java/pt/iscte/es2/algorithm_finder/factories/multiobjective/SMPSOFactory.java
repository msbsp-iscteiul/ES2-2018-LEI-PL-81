package pt.iscte.es2.algorithm_finder.factories.multiobjective;

import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSO;
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSOBuilder;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmBuilder;
import org.uma.jmetal.util.archive.impl.HypervolumeArchive;
import pt.iscte.es2.algorithm_finder.AlgorithmConstants;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

@BuilderTypes(
	algorithm = SMPSO.class,
	solutionType = DoubleSolution.class
)
public class SMPSOFactory implements AlgorithmBuilder<SMPSO> {
	private final DoubleProblem problem;

	public SMPSOFactory(DoubleProblem problem) {
		this.problem = problem;
	}

	@Override
	public SMPSO build() {
		return new SMPSOBuilder(
			problem,
			new HypervolumeArchive<>(
				problem.getNumberOfVariables(),
				new PISAHypervolume<>()
			)
		)
			.setMaxIterations(AlgorithmConstants.MAX_EVALUTIONS / 100)
			.build();
	}
}
