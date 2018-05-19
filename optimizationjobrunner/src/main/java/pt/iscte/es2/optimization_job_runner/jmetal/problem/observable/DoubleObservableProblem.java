package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.DoubleSolution;

/**
 * {@inheritDoc}
 */
public class DoubleObservableProblem extends ObservableProblem<DoubleSolution> implements DoubleProblem {
	/**
	 * {@inheritDoc}
	 */
	public DoubleObservableProblem(Problem<DoubleSolution> innerProblem) {
		super(innerProblem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getLowerBound(int index) {
		return ((DoubleProblem) innerProblem).getLowerBound(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getUpperBound(int index) {
		return ((DoubleProblem) innerProblem).getUpperBound(index);
	}
}
