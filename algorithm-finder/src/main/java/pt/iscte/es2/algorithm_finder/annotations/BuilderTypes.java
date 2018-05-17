package pt.iscte.es2.algorithm_finder.annotations;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.Solution;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotate which algorithm and solution types the factories are compatible with
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BuilderTypes {
	Class<? extends Algorithm> algorithm();
	Class<? extends Solution> solutionType();
}
