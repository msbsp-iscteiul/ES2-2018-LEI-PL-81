package pt.iscte.es2.api;

import pt.iscte.es2.dto.State;
import pt.iscte.es2.dto.service.optimization.*;

/**
 * OptimizationConfiguration Service API
 */
public interface OptimizationService {

	/**
	 * Persists the optimization suggested by the user which contains Variables, Objectives,
	 * AlgorithmChoiceMethod and CSV File
	 *
	 * @param request
	 * 			SaveOptimizationConfigurationRequest
	 *
	 * @return SaveOptimizationConfigurationResponse
	 */
	public SaveOptimizationConfigurationResponse saveOptimization(SaveOptimizationConfigurationRequest request);

	/**
	 * Returns the filePath of the JAR file of the current SessionId.
	 *
	 * @param sessionId
	 * 			String
	 */
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

	/**
	 * Searchs for an OptimizationConfiguration by an existing Id and Email
	 *
	 * @param request
	 * 			SearchOptimizationConfigurationByIdAndEmailRequest
	 *
	 * @return SearchOptimizationConfigurationByIdAndEmailResponse
	 */
	public SearchOptimizationConfigurationByIdAndEmailResponse searchOptimizationConfigurationByIdAndEmail(
		SearchOptimizationConfigurationByIdAndEmailRequest request);

	/**
	 * Searchs for all SummaryOptimizationConfiguration by Email
	 *
	 * @param request
	 * 			SearchOptimizationConfigurationByEmailRequest
	 *
	 * @return SearchOptimizationConfigurationByEmailResponse
	 */
	public SearchOptimizationConfigurationByEmailResponse searchOptimizationConfigurationByEmail(
		SearchOptimizationConfigurationByEmailRequest request);

	/**
	 * Executes an OptimizationConfiguration By ID and the User Email
	 *
	 * @param request
	 * 			ExecuteOptimizationConfigurationRequest
	 *
	 * @return ExecuteOptimizationConfigurationResponse
	 */
	public ExecuteOptimizationConfigurationResponse executeOptimizationConfiguration(
		ExecuteOptimizationConfigurationRequest request);

	/**
	 * Persists a list of OptimizationJobSolution given an OptimizationJobExecution ID
	 *
	 * @param request
	 * 			ExecuteOptimizationConfigurationRequest
	 *
	 * @return ExecuteOptimizationConfigurationResponse
	 */
	public SaveOptimizationJobSolutionResponse saveOptimizationJobSolution(SaveOptimizationJobSolutionRequest request);

	/**
	 * Updates the State of an Optimization Job Execution By ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param state
	 * 			State
	 */
	public void updateState(Integer id, State state);

	/**
	 * Returns the JAR File associated with an Job Execution By Id
	 *
	 * @param request
	 * 			OptimizationConfigurationAttachmentRequest
	 *
	 * @return OptimizationConfigurationAttachmentResponse
	 */
	public OptimizationConfigurationAttachmentResponse searchAttachmentByJobExecution(
		OptimizationConfigurationAttachmentRequest request);

}
