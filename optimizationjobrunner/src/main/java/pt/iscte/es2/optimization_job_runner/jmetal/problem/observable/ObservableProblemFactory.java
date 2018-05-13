package pt.iscte.es2.optimization_job_runner.jmetal.problem.observable;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.problem.Problem;

public class ObservableProblemFactory {

	public ObservableProblem createFromProblem(Problem problem) {

		if (problem instanceof DoubleProblem) {
			return new DoubleObservableProblem(problem);
		} else if (problem instanceof IntegerProblem) {
			return new IntegerObservableProblem(problem);
		} else if (problem instanceof BinaryProblem) {
			return new BinaryObservableProblem(problem);
		}

		return new ObservableProblem(problem);
	}
}
