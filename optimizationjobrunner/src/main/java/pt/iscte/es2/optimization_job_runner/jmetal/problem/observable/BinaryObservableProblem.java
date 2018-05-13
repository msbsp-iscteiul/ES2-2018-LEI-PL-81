package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;

public class BinaryObservableProblem extends ObservableProblem<BinarySolution> implements BinaryProblem {
	public BinaryObservableProblem(Problem<BinarySolution> innerProblem) {
		super(innerProblem);
	}

	@Override
	public int getNumberOfBits(int index) {
		return ((BinaryProblem) innerProblem).getNumberOfBits(index);
	}

	@Override
	public int getTotalNumberOfBits() {
		return ((BinaryProblem) innerProblem).getTotalNumberOfBits();
	}
}
