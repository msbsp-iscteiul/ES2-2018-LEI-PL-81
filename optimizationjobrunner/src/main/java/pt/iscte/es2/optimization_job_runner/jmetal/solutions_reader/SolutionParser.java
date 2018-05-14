package pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionParser implements FileReaderParser<List<Double>> {
	@Override
	public List<Double> parse(String line) {
		final String[] positiveNegative = line.split("\\s+");
		return Arrays
			.stream(positiveNegative)
			.map(Double::parseDouble)
			.collect(Collectors.toList());
	}
}
