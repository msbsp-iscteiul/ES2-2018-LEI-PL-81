package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.BinarySolution;

/**
 * {@inheritDoc}
 */
public class BinaryObservableProblem extends ObservableProblem<BinarySolution> implements BinaryProblem {
	/**
	 * {@inheritDoc}
	 */
	public BinaryObservableProblem(Problem<BinarySolution> innerProblem) {
		super(innerProblem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfBits(int index) {
		return ((BinaryProblem) innerProblem).getNumberOfBits(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTotalNumberOfBits() {
		return ((BinaryProblem) innerProblem).getTotalNumberOfBits();
	}
}
