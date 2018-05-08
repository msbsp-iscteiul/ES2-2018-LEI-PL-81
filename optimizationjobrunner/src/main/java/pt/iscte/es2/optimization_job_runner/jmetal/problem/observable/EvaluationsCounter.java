package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.solution.Solution;

public class EvaluationsCounter implements SolutionEvaluationListener {

	private int counter = 0;

	@Override
	public void onSolutionEvaluated(Solution solution) {
		counter++;
	}

	public int getCount() {
		return counter;
	}
}
