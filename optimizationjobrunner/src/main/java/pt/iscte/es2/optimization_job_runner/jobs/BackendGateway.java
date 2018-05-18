package pt.iscte.es2.optimization_job_runner.jobs;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pt.iscte.es2.optimization_job_runner.post_processing.AlgorithmSolutionQuality;
import pt.iscte.es2.optimization_job_runner.post_processing.OptimizationJobResult;

/**
 * Gateway to access the backend service
 */
public class BackendGateway {
	private final RestTemplate restTemplate;
	private final String backendBaseUrl;

	/**
	 * Constructor
	 * @param restTemplate restful services
	 * @param backendBaseUrl backend service url
	 */
	public BackendGateway(RestTemplate restTemplate, String backendBaseUrl) {
		this.restTemplate = restTemplate;
		this.backendBaseUrl = backendBaseUrl;
	}

	/**
	 * Saves a finished job
	 * @param optimizationJobResult the finished job
	 */
	public void saveFinishedOptimizationJobSolution(OptimizationJobResult optimizationJobResult) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", optimizationJobResult.getJobId());
		multiValueMap.add("latex", new FileSystemResource(optimizationJobResult.getLatexPdf()));
		multiValueMap.add("r", new FileSystemResource(optimizationJobResult.getrEps()));
		multiValueMap.add("state", "Finished");

		int i = 0;
		for (AlgorithmSolutionQuality algorithmSolutionQuality : optimizationJobResult.getBestSolutions()) {
			multiValueMap.add("solutions[" + i + "].algorithmName", algorithmSolutionQuality.getAlgorithmName());
			multiValueMap.add("solutions[" + i + "].solution", algorithmSolutionQuality.getSolution());
			multiValueMap.add("solutions[" + i + "].solutionQuality", algorithmSolutionQuality.getSolutionQuality().getStringSolutionQuality());
			multiValueMap.add("solutions[" + i + "].quality", algorithmSolutionQuality.getSolutionQuality().quality());
			++i;
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, headers);
		restTemplate.exchange(
			backendBaseUrl + "/api/optimization/saveoptimizationjobsolution/",
			HttpMethod.POST, requestEntity, String.class);
	}

	/**
	 * Saves a failed job
	 * @param jobId the failed job id
	 */
	public void failOptimizationJob(long jobId) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", jobId);
		multiValueMap.add("state", "Failed");
		restTemplate.exchange(
			backendBaseUrl + "/api/optimization/saveoptimizationjobsolution/",
			HttpMethod.POST,
			new HttpEntity<>(multiValueMap),
			String.class
		);
	}
}
