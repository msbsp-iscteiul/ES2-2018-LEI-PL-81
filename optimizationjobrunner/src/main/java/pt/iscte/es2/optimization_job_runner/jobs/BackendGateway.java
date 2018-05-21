package pt.iscte.es2.optimization_job_runner.jobs;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pt.iscte.es2.optimization_job_runner.post_processing.AlgorithmSolutionQuality;
import pt.iscte.es2.optimization_job_runner.post_processing.OptimizationJobResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

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
		try {
			restTemplate.exchange(
				backendBaseUrl + "/api/optimization/saveoptimizationjobsolution/",
				HttpMethod.POST, requestEntity, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves a failed job
	 * @param jobId the failed job id
	 */
	public void failOptimizationJob(long jobId) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", jobId);
		multiValueMap.add("state", "Failed");
		try {
			restTemplate.exchange(
				backendBaseUrl + "/api/optimization/saveoptimizationjobsolution/",
				HttpMethod.POST,
				new HttpEntity<>(multiValueMap),
				String.class
			);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the job status in the backend to run
	 * @param jobId the running job id
	 */
	public void runOptimizationJob(long jobId) {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", jobId);
		multiValueMap.add("state", "Running");
		try {
			restTemplate.exchange(
				backendBaseUrl + "/api/optimization/updateoptimizationjobexecution/",
				HttpMethod.POST,
				new HttpEntity<>(multiValueMap),
				String.class
			);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the queued job from the backend
	 * @param id the job id
	 * @param email the client email
	 * @return
	 * @throws RestClientException
	 * @throws IOException
	 */
	public Job getConfigurationOfId(long id, String email) throws RestClientException, IOException {
		final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", id);
		multiValueMap.add("email", email);
		final OptimizationConfiguration configuration = restTemplate.exchange(
			backendBaseUrl + "/api/optimization/searchoptimizationconfigurationbyidandemail/",
			HttpMethod.POST,
			new HttpEntity<>(multiValueMap),
			OptimizationConfiguration.class
		).getBody();
		final String jarPath = downloadProblem(id);
		return new Job(id, configuration.getProblemName(), jarPath, configuration.getEmail(), configuration.getWaitingTime());
	}

	/**
	 * Downloads the jar problem from the backend
	 * @param jobId the job id
	 * @return path to the created jar
	 * @throws IOException
	 */
	public String downloadProblem(long jobId) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(
			MediaType.APPLICATION_OCTET_STREAM
		));
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("id", jobId);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, httpHeaders);
		ResponseEntity<byte[]> response = new RestTemplate()
			.exchange(
				backendBaseUrl + "/api/optimization/searchattachmentbyjobexecution/",
				HttpMethod.POST,
				entity,
				byte[].class
			);
		byte[] file = response.getBody();
		if (file != null) {
			File tempFile = File.createTempFile("problem", ".jar");
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(file);
			return tempFile.getAbsolutePath();
		}
		return null;
	}
}
