package pt.iscte.es2;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class Plugin extends AbstractDoubleProblem {

	/**
	 * Constructor.
	 * Creates a default instance of the Kursawe problem.
	 */
	public Plugin() {
		// 3 variables by default
		this(3);
	}

	/**
	 * Constructor.
	 * Creates a new instance of the Kursawe problem.
	 *
	 * @param numberOfVariables Number of variables of the problem
	 */
	public Plugin(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpam");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/** Evaluate() method */
	public void evaluate(DoubleSolution solution){
		double aux, xi, xj;
		double[] fx = new double[getNumberOfObjectives()];
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i) ;
		}

		fx[0] = 0.0;
		for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
			xi = x[var] * x[var];
			xj = x[var + 1] * x[var + 1];
			aux = (-0.1) * Math.sqrt(xi + xj);
			fx[0] += (-5.0) * Math.exp(aux);
		}

		fx[1] = 0.0;

		for (int var = 0; var < solution.getNumberOfVariables(); var++) {
			fx[1] += Math.pow(Math.abs(x[var]), 0.3) +
				2.0 * Math.sin(Math.pow(x[var], 1.0));
		}

		solution.setObjective(0, fx[0]);
		solution.setObjective(1, fx[1]);
	}
}
