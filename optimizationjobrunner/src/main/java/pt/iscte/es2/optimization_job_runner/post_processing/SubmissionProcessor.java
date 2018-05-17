package pt.iscte.es2.optimization_job_runner.post_processing;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Submits the result to the backend
 */
public class SubmissionProcessor implements PostProblemProcessor {

	private final String backendHost;
	private final String backendPort;

	/**
	 * Constructor
	 * @param backendHost backend host
	 * @param backendPort backend port
	 */
	public SubmissionProcessor(String backendHost, String backendPort) {
		this.backendHost = backendHost;
		this.backendPort = backendPort;
	}

	/**
	 * Submits the result to the backend
	 * @param result the subject for processing
	 */
	@Override
	public void process(OptimizationJobResult result) {

		final MultiValueMap<String, Object> multiValueMap = adaptResultToMap(result);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, headers);
		System.out.println(requestEntity.toString());

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(
			"http://" + backendHost + ":" + backendPort + "/api/optimization/saveoptimizationjobsolution/",
			HttpMethod.POST, requestEntity, String.class);
	}

	/**
	 * Adapts the job result to a map consumable by the REST service
	 * @param result the subject
	 * @return a map consumable by the REST service
	 */
	private MultiValueMap<String, Object> adaptResultToMap(OptimizationJobResult result) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", result.getJobId());
		multiValueMap.add("latex", new FileSystemResource(result.getLatexPdf()));
		multiValueMap.add("r", new FileSystemResource(result.getrEps()));
		multiValueMap.add("state", "Finished");

		int i = 0;
		for (AlgorithmSolutionQuality algorithmSolutionQuality : result.getBestSolutions()) {
			multiValueMap.add("solutions[" + i + "].algorithmName", algorithmSolutionQuality.getAlgorithmName());
			multiValueMap.add("solutions[" + i + "].solution", algorithmSolutionQuality.getSolution());
			multiValueMap.add("solutions[" + i + "].solutionQuality", algorithmSolutionQuality.getSolutionQuality().getStringSolutionQuality());
			multiValueMap.add("solutions[" + i + "].quality", algorithmSolutionQuality.getSolutionQuality().quality());
			++i;
		}
		return multiValueMap;
	}
}
