package pt.iscte.es2.algorithm_finder;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.uma.jmetal.problem.Problem;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AlgorithmFinder {
	private final Problem problem;

	public AlgorithmFinder(Problem problem) {
		this.problem = problem;
	}

        public List<Constructor<?>> execute() {
		Class<?> problemSolutionType = getSolutionTypeForProblem();
		if (problemSolutionType == null) {
			return Collections.emptyList();
		}

                List<Constructor<?>> constructors = new ArrayList<>();
		Reflections reflections = buildReflector();

		for (Class<?> aClass : reflections.getTypesAnnotatedWith(BuilderTypes.class)) {
			final Class annotatedSolutionType = aClass.getAnnotation(BuilderTypes.class).solutionType();

			// Ensures solution type is compatible with the algorithm
			// noinspection unchecked
			if (!annotatedSolutionType.isAssignableFrom(problemSolutionType)) {
				continue;
			}

			// Ensures the constructor is compatible with the problem
			// May be easier with method in an interface
			for (Constructor<?> constructor : aClass.getConstructors()) {
				final Class<?> constructorType = constructor.getParameterTypes()[0];
				if (constructorType.isAssignableFrom(problem.getClass())) {
                                        constructors.add(constructor);
					break;
				}
			}
		}
                return constructors;
	}

	private Class<?> getSolutionTypeForProblem() {
		final Method[] declaredMethods = problem.getClass().getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if (declaredMethod.getName().equals("evaluate") && !declaredMethod.isBridge()) {
				return declaredMethod.getParameters()[0].getType();
			}
		}
		return null;
	}

	private static Reflections buildReflector() {
		List<ClassLoader> classLoadersList = new LinkedList<>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		return new Reflections(new ConfigurationBuilder()
			.setScanners(
				new SubTypesScanner(false /* don't exclude Object.class */),
				new ResourcesScanner(),
				new TypeAnnotationsScanner()
			)
			.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
			.filterInputsBy(
				new FilterBuilder()
					.include(FilterBuilder.prefix("pt.iscte.es2.algorithm_finder.factories"))
			));
	}
}
