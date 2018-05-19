package pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader;

import org.uma.jmetal.problem.Problem;

/**
 * Solution parser
 */
public class SolutionParser implements FileReaderParser<String> {
	/**
	 * Parses a solution for a {@link Problem}
	 * @param line to be parsed
	 * @return Solution string
	 */
	@Override
	public String parse(String line) {
		return line;
	}
}
