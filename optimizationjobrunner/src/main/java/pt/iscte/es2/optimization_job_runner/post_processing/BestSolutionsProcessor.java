package pt.iscte.es2.optimization_job_runner.post_processing;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import pt.iscte.es2.optimization_job_runner.jmetal.solution.SolutionQuality;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionParser;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionQualityParser;
import pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader.SolutionsFileReader;
import pt.iscte.es2.optimization_job_runner.jobs.Job;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Processes the job result to find the best solutions
 */
public class BestSolutionsProcessor implements PostProblemProcessor {
	/**
	 * Processes the job result to find the best solutions
	 * @param result the job result
	 */
	@Override
	public void process(OptimizationJobResult result) {
		final PostProcessingContext context = result.getContext();
		final SolutionsFileReader<SolutionQuality> solutionQualityReader = new SolutionsFileReader<>(new SolutionQualityParser());
		final SolutionsFileReader<String> solutionsReader = new SolutionsFileReader<>(new SolutionParser());
		final List<AlgorithmSolutionQuality> algorithmSolutionQualities = new ArrayList<>();
		final Job job = context.getJob();
		for (ExperimentAlgorithm<Solution<?>, List<Solution<?>>> algorithm : context.getAlgorithms()) {
			final String algorithmSolutionQualityFileName = String.format("%s/%s.%s.rf",
				context.getSolutionsPath(), job.getProblemName(), algorithm.getAlgorithmTag());
			final String algorithmSolutionFileName = String.format("%s/%s.%s.rs",
				context.getSolutionsPath(), job.getProblemName(), algorithm.getAlgorithmTag());
			try {
				final List<SolutionQuality> solutionQualities = solutionQualityReader.readFile(new FileReader(algorithmSolutionQualityFileName));
				final List<String> algorithmSolutions = solutionsReader.readFile(new FileReader(algorithmSolutionFileName));
				int i = 0;
				for (SolutionQuality solutionQuality : solutionQualities) {
					final String solution = algorithmSolutions.get(i++);
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
		result.setBestSolutions(bestSolutions);
	}
}
