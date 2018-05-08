package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.solution.Solution;

public interface SolutionEvaluationListener<S extends Solution<?>> {
	public void onSolutionEvaluated(S solution);
}
