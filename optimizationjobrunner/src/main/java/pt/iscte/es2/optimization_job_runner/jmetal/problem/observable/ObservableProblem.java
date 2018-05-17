package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem encapsulator that notifies observers for evaluations
 * @param <S>
 */
public class ObservableProblem<S extends Solution<?>> implements Problem<S> {
	final Problem<S> innerProblem;
	final List<SolutionEvaluationListener<S>> evaluationListeners = new ArrayList<>();

	/**
	 * Constructor
	 * @param innerProblem encapsulated problem
	 */
	public ObservableProblem(Problem<S> innerProblem) {
		this.innerProblem = innerProblem;
	}

	/**
	 * Register a listener for the {@link Problem}
	 * @param listener the listener
	 */
	public void registerEvaluationListener(SolutionEvaluationListener<S> listener) {
		evaluationListeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfVariables() {
		return innerProblem.getNumberOfVariables();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfObjectives() {
		return innerProblem.getNumberOfObjectives();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfConstraints() {
		return innerProblem.getNumberOfConstraints();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return innerProblem.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void evaluate(S solution) {
		innerProblem.evaluate(solution);
		evaluationListeners.forEach(listener -> listener.onSolutionEvaluated(solution));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public S createSolution() {
		return innerProblem.createSolution();
	}
}
