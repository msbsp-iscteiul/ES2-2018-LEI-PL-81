package pt.iscte.es2.optimization_job_runner.post_processing;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import pt.iscte.es2.optimization_job_runner.jmetal.solution.SolutionQuality;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionParser;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionQualityParser;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionsFileReader;
import pt.iscte.es2.optimization_job_runner.post_processing.result_compilers.RToEpsCompiler;
import pt.iscte.es2.optimization_job_runner.post_processing.result_compilers.TexToPdfCompiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PostProblemProcessor {

	private final String experimentName;
	private final String problemName;
	private final String solutionsPath;
	private final String experimentPath;
	private final List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms;

	public PostProblemProcessor(
		String experimentName,
		String problemName,
		String solutionsPath,
		String experimentPath,
		List<ExperimentAlgorithm<Solution<?>, List<Solution<?>>>> algorithms
	) {
		this.experimentName = experimentName;
		this.problemName = problemName;
		this.solutionsPath = solutionsPath;
		this.experimentPath = experimentPath;
		this.algorithms = algorithms;
	}

	public void execute() {
		try {
			final File latexPdf = new TexToPdfCompiler().compile(experimentPath + "/latex/" + experimentName + ".tex");
			System.out.println(latexPdf);
			final File rEps = new RToEpsCompiler().compile(experimentPath + "/R/HV.Boxplot.R");
			System.out.println(rEps);
		} catch (IOException | InterruptedException ignore) {
		}

		final SolutionsFileReader<SolutionQuality> solutionQualityReader = new SolutionsFileReader<>(new SolutionQualityParser());
		final SolutionsFileReader<List<Double>> solutionsReader = new SolutionsFileReader<>(new SolutionParser());
		final List<AlgorithmSolutionQuality> algorithmSolutionQualities = new ArrayList<>();
		for (ExperimentAlgorithm<Solution<?>, List<Solution<?>>> algorithm : algorithms) {
			final String algorithmSolutionQualityFileName = String.format("%s/%s.%s.rf",
				solutionsPath, algorithm.getProblemTag(), algorithm.getAlgorithmTag());
			final String algorithmSolutionFileName = String.format("%s/%s.%s.rs",
				solutionsPath, algorithm.getProblemTag(), algorithm.getAlgorithmTag());
			try {
				final List<SolutionQuality> solutionQualities = solutionQualityReader.readFile(new FileReader(algorithmSolutionQualityFileName));
				final List<List<Double>> algorithmSolutions = solutionsReader.readFile(new FileReader(algorithmSolutionFileName));
				int i = 0;
				for (SolutionQuality solutionQuality : solutionQualities) {
					final List<Double> solution = algorithmSolutions.get(i++);
					algorithmSolutionQualities.add(
						new AlgorithmSolutionQuality(
							algorithm.getAlgorithm().getName(),
							solution,
							solutionQuality
						)
					);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		final List<AlgorithmSolutionQuality> bestSolutions = algorithmSolutionQualities.stream()
			.sorted(Comparator.comparingDouble(AlgorithmSolutionQuality::quality))
			.limit(10).collect(Collectors.toList());

		bestSolutions.forEach(p -> System.out.println(p.quality()));
		System.out.println(algorithmSolutionQualities);
	}
}
