package pt.iscte.es2.optimization_job_runner.jmetal.solution;

import java.util.List;

public class SolutionQuality {
	private final List<Double> objectivesValue;

	public SolutionQuality(List<Double> objectiveValue) {
		this.objectivesValue = objectiveValue;
	}

	public double quality() {
		double sum = 0.0;
		for (Double objectiveValue : objectivesValue) {
			sum += objectiveValue * objectiveValue;
		}
		return Math.sqrt(sum);
	}

	public List<Double> getObjectivesValue() {
		return objectivesValue;
	}
}
