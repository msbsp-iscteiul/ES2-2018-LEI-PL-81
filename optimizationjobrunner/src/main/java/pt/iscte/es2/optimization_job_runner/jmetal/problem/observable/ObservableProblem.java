package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;

public class ObservableProblem<S extends Solution<?>> implements Problem<S> {
	final Problem<S> innerProblem;
	final List<SolutionEvaluationListener<S>> evaluationListeners = new ArrayList<>();

	public ObservableProblem(Problem<S> innerProblem) {
		this.innerProblem = innerProblem;
	}

	public void registerEvaluationListener(SolutionEvaluationListener<S> listener) {
		evaluationListeners.add(listener);
	}

	@Override
	public int getNumberOfVariables() {
		return innerProblem.getNumberOfVariables();
	}

	@Override
	public int getNumberOfObjectives() {
		return innerProblem.getNumberOfObjectives();
	}

	@Override
	public int getNumberOfConstraints() {
		return innerProblem.getNumberOfConstraints();
	}

	@Override
	public String getName() {
		return innerProblem.getName();
	}

	@Override
	public void evaluate(S solution) {
		innerProblem.evaluate(solution);
		evaluationListeners.forEach(listener -> listener.onSolutionEvaluated(solution));
	}

	@Override
	public S createSolution() {
		return innerProblem.createSolution();
	}
}
