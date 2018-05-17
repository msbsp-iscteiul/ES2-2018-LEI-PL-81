package pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader;

public class SolutionParser implements FileReaderParser<String> {
	@Override
	public String parse(String line) {
		return line;
	}
}
