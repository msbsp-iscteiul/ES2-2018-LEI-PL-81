package pt.iscte.es2.algorithm_finder.annotations;

import org.uma.jmetal.solution.Solution;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuilderTypes {
	Class<? extends Solution> solutionType();
}
