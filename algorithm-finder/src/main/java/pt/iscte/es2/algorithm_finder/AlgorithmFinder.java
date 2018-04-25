package pt.iscte.es2.algorithm_finder;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.Problem;
import pt.iscte.es2.algorithm_finder.annotations.BuilderTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AlgorithmFinder {
	private final Problem problem;

	public AlgorithmFinder(Problem problem) {
		this.problem = problem;
	}

	public AlgorithmFinderResult execute() {
		Class<?> problemSolutionType = getSolutionTypeForProblem();
		if (problemSolutionType == null) {
			return new AlgorithmFinderResult();
		}

		final List<Class<?>> factories = new ArrayList<>();
		final List<Constructor<?>> constructors = new ArrayList<>();
		Reflections reflections = buildReflector();

		for (Class<?> aClass : reflections.getTypesAnnotatedWith(BuilderTypes.class)) {
			final Class annotatedSolutionType = aClass.getAnnotation(BuilderTypes.class).solutionType();

			// Ensures solution type is compatible with the algorithm
			// noinspection unchecked
			if (!annotatedSolutionType.isAssignableFrom(problemSolutionType)) {
				continue;
			}
			factories.add(aClass);

			// Ensures the constructor is compatible with the problem
			// May be easier with method in an interface
			for (Constructor<?> constructor : aClass.getConstructors()) {
				// get first constructor
				final Class<?> constructorType = constructor.getParameterTypes()[0];
				// check compatibility and break if found
				if (constructorType.isAssignableFrom(problem.getClass())) {
					constructors.add(constructor);
					break;
				}
			}
		}
		return new AlgorithmFinderResult(factories, constructors, problemSolutionType.getName());
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

	public class AlgorithmFinderResult {
		private final List<Class<?>> algorithmFactories;
		private final List<Constructor<?>> constructors;
		private final String solutionTypeName;

		private AlgorithmFinderResult(
			List<Class<?>> algorithmFactories,
			List<Constructor<?>> constructors,
			String solutionTypeName
		) {
			this.algorithmFactories = algorithmFactories;
			this.constructors = constructors;
			this.solutionTypeName = solutionTypeName;
		}

		private AlgorithmFinderResult() {
			algorithmFactories = Collections.emptyList();
			constructors = Collections.emptyList();
			solutionTypeName = null;
		}

		public List<String> getAlgorithms() {
			return algorithmFactories.stream()
				.map(factory -> factory.getAnnotation(BuilderTypes.class).algorithm().getName())
				.collect(Collectors.toList());
		}

		public List<Constructor<?>> getConstructors() {
			return constructors;
		}

		public List<Constructor<?>> getConstructorsForAlgorithms(List<String> selectedAlgorithms) {
			final Map<String, String> algorithms = algorithmFactories.stream()
				.collect(Collectors.toMap(
					factory -> factory.getAnnotation(BuilderTypes.class).algorithm().getName(),
					Class::getName
				));
			return constructors.stream()
				.filter(constructor -> {
					for (String selectedAlgorithm : selectedAlgorithms) {
						if (constructor.getName().equals(algorithms.get(selectedAlgorithm))) {
							return true;
						}
					}
					return false;
				}).collect(Collectors.toList());
		}

		public String getSolutionTypeName() {
			return solutionTypeName;
		}
	}
}
