package pt.iscte.es2.optimization_job_runner.jmetal.solutions_reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SolutionsFileReader<P> {

	private final FileReaderParser<P> parser;
	private List<P> results = new ArrayList<>();

	public SolutionsFileReader(FileReaderParser<P> parser) {
		this.parser = parser;
	}

	/**
	 * Reads a file and returns the result
	 *
	 * @param inReader to read
	 * @return list or results of R type
	 * @throws IOException input or output expection
	 */
	public List<P> readFile(Reader inReader) throws IOException {
		try (BufferedReader reader = new BufferedReader(inReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				results.add(parser.parse(line));
			}
		}
		final List<P> aux = results;
		results = new ArrayList<>();
		return aux;
	}
}
