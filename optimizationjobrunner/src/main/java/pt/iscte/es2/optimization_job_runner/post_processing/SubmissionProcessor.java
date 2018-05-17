package pt.iscte.es2.optimization_job_runner.post_processing;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SubmissionProcessor implements PostProblemProcessor {

	private final String backendHost;
	private final String backendPort;

	public SubmissionProcessor(String backendHost, String backendPort) {
		this.backendHost = backendHost;
		this.backendPort = backendPort;
	}

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

	private MultiValueMap<String, Object> adaptResultToMap(OptimizationJobResult result) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", result.getJobId());
		multiValueMap.add("latex", new FileSystemResource(result.getLatexPdf()));
		multiValueMap.add("r", new FileSystemResource(result.getrEps()));

		int i = 0;
		for (AlgorithmSolutionQuality algorithmSolutionQuality : result.getBestSolutions()) {
			multiValueMap.add("solutions[" + i + "].algorithmName", algorithmSolutionQuality.getAlgorithmName());
			multiValueMap.add("solutions[" + i + "].solution", algorithmSolutionQuality.getSolution());
			multiValueMap.add("solutions[" + i + "].solutionQuality", algorithmSolutionQuality.getSolutionQuality().getStringSolution());
			multiValueMap.add("solutions[" + i + "].quality", algorithmSolutionQuality.getSolutionQuality().quality());
			++i;
		}
		return multiValueMap;
	}
}
