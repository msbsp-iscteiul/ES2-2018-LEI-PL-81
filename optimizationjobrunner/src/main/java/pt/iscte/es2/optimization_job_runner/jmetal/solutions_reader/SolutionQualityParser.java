package pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader;

import pt.iscte.es2.optimization_job_runner.jmetal.solution.SolutionQuality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionQualityParser implements FileReaderParser<SolutionQuality> {
	@Override
	public SolutionQuality parse(String line) {
		final String[] positiveNegative = line.split("\\s+");
		final List<Double> objectiveValues = Arrays
			.stream(positiveNegative)
			.map(Double::parseDouble)
			.collect(Collectors.toList());
		return new SolutionQuality(objectiveValues, line);
	}
}
