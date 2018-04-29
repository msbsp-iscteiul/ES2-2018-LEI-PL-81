package pt.iscte.es2.api;

import pt.iscte.es2.dto.service.optimization.FileUploadRequest;
import pt.iscte.es2.dto.service.optimization.FileUploadResponse;
import pt.iscte.es2.dto.service.optimization.OptimizationRequest;
import pt.iscte.es2.dto.service.optimization.OptimizationResponse;

/**
 * OptimizationConfiguration Service API
 */
public interface OptimizationService {

	/**
	 * Persists the optimization suggested by the user which contains Variables, Objectives,
	 * AlgorithmChoiceMethod and CSV File
	 *
	 * @param request
	 * 			OptimizationRequest
	 *
	 * @return OptimizationResponse
	 */
	public OptimizationResponse saveOptimization(OptimizationRequest request);


	public void searchFilePathBySessionId(String sessionId);

	/**
	 * Uploads a Jar file that contains a problem to be optimized and returns
	 * a result that contains all the necessary data such as NumberOfVariables, NumberOfObjectives.
	 *
	 * @param request
	 *
	 * @return {@link FileUploadResponse}
	 */
	public FileUploadResponse fileUpload(FileUploadRequest request);
}
