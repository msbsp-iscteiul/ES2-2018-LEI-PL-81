package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.solution.Solution;

/**
 * Listens for evaluations in an {@link ObservableProblem}
 * @param <S>
 */
public interface SolutionEvaluationListener<S extends Solution<?>> {
	void onSolutionEvaluated(S solution);
}
