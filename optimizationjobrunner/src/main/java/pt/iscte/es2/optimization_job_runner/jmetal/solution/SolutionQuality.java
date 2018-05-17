package pt.iscte.es2.optimization_job_runner.jmetal.solution;

import java.util.List;

public class SolutionQuality {
	private final List<Double> objectivesValue;
	private final String stringSolution;

	public SolutionQuality(List<Double> objectiveValue, String stringSolution) {
		this.objectivesValue = objectiveValue;
		this.stringSolution = stringSolution;
	}

	public double quality() {
		double sum = 0.0;
		for (Double objectiveValue : objectivesValue) {
			sum += objectiveValue * objectiveValue;
		}
		return Math.sqrt(sum);
	}

	public String getStringSolution() {
		return stringSolution;
	}
}
