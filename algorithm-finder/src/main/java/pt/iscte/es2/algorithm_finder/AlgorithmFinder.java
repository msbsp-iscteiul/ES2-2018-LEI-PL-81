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

/**
 * Finds algorithms matching a {@link Problem} type
 */
public class AlgorithmFinder {
	private final Problem problem;

	/**
	 * The constructor
	 * @param problem the problem
	 */
	public AlgorithmFinder(Problem problem) {
		this.problem = problem;
	}

	/**
	 * Execute the finder
	 * @return finding results
	 */
	public AlgorithmFinderResult execute() {
		Class<?> problemSolutionType = getSolutionTypeForProblem();

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

	/**
	 * Determines the type of solution for the given type
	 * @return the type
	 */
	private Class<?> getSolutionTypeForProblem() {
		final Method[] declaredMethods = problem.getClass().getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if (declaredMethod.getName().equals("evaluate") && !declaredMethod.isBridge()) {
				return declaredMethod.getParameters()[0].getType();
			}
		}
		return Object.class;
	}

	/**
	 * Builds the reflector to navigate the code in search of compatible types
	 * @return the reflector
	 */
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

	/**
	 * The result class for {@link AlgorithmFinder}.
	 * - List of algorithm factories compatible with a {@link Problem}.
	 * - List of constructors compatible with a {@link Problem}
	 * - Has the solution type name
	 */
	public class AlgorithmFinderResult {
		private final List<Class<?>> algorithmFactories;
		private final List<Constructor<?>> constructors;
		private final String solutionTypeName;

		/**
		 * The constructor
		 * @param algorithmFactories algorithm factories for {@link Problem}
		 * @param constructors constructors for {@link Problem}
		 * @param solutionTypeName solution type name for {@link Problem}
		 */
		private AlgorithmFinderResult(
			List<Class<?>> algorithmFactories,
			List<Constructor<?>> constructors,
			String solutionTypeName
		) {
			this.algorithmFactories = algorithmFactories;
			this.constructors = constructors;
			this.solutionTypeName = solutionTypeName;
		}

		/**
		 * The list of algorithms
		 * @return list of algorithms
		 */
		public List<String> getAlgorithms() {
			return algorithmFactories.stream()
				.map(factory -> factory.getAnnotation(BuilderTypes.class).algorithm().getName())
				.collect(Collectors.toList());
		}

		/**
		 * The list of constructors
		 * @return list of constructors
		 */
		public List<Constructor<?>> getConstructors() {
			return constructors;
		}

		/**
		 * List of constructors for selected algorithms
		 * @param selectedAlgorithms selected algorithms
		 * @return list of constructors
		 */
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
