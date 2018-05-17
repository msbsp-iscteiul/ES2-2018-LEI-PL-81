package pt.iscte.es2.optimization_job_runner.jmetal.solution;

import java.util.List;

/**
 * Value object representing the quality of a solution
 */
public class SolutionQuality {
	private final List<Double> doubleListSolutionQuality;
	private final String stringSolutionQuality;

	/**
	 * Constructor
	 * @param doubleListSolutionQuality quality as double
	 * @param stringSolutionQuality quality as string
	 */
	public SolutionQuality(List<Double> doubleListSolutionQuality, String stringSolutionQuality) {
		this.doubleListSolutionQuality = doubleListSolutionQuality;
		this.stringSolutionQuality = stringSolutionQuality;
	}

	/**
	 * General quality of solution
	 * @return quality
	 */
	public double quality() {
		double sum = 0.0;
		for (Double objectiveValue : doubleListSolutionQuality) {
			sum += objectiveValue * objectiveValue;
		}
		return Math.sqrt(sum);
	}

	/**
	 * String solution quality
	 * @return solution quality
	 */
	public String getStringSolutionQuality() {
		return stringSolutionQuality;
	}
}
