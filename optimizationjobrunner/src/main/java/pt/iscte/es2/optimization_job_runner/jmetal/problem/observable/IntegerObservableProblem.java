package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.IntegerSolution;

public class IntegerObservableProblem extends ObservableProblem<IntegerSolution> implements IntegerProblem {
	/**
	 * {@inheritDoc}
	 */
	public IntegerObservableProblem(Problem<IntegerSolution> innerProblem) {
		super(innerProblem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getLowerBound(int index) {
		return ((IntegerProblem) innerProblem).getLowerBound(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getUpperBound(int index) {
		return ((IntegerProblem) innerProblem).getUpperBound(index);
	}
}
