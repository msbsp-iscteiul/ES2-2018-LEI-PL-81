package pt.iscte.es2.api;

import org.springframework.core.io.FileSystemResource;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.State;
import pt.iscte.es2.dto.service.optimization.*;

/**
 * OptimizationConfiguration Service API
 */
public interface OptimizationService {

	/**
	 * Persists an OptimizationConfiguration suggested by the user which contains Variables, Objectives,
	 * AlgorithmChoiceMethod and CSV File
	 *
	 * @param request
	 * 			{@link SaveOptimizationConfigurationRequest}
	 *
	 * @return {@link SaveOptimizationConfigurationResponse}
	 */
	public SaveOptimizationConfigurationResponse saveOptimization(SaveOptimizationConfigurationRequest request);

	/**
	 * Uploads a Jar file that contains a problem to be optimized and returns a result that
	 * contains all the necessary data such as NumberOfVariables, NumberOfObjectives.
	 *
	 * @param request
	 * 			{@link FileUploadRequest}
	 *
	 * @return {@link FileUploadResponse}
	 */
	public FileUploadResponse fileUpload(FileUploadRequest request);

	/**
	 * Searchs for an OptimizationConfiguration by an existing Id and Email
	 *
	 * @param request
	 * 			{@link SearchOptimizationConfigurationByIdAndEmailRequest}
	 *
	 * @return {@link SearchOptimizationConfigurationByIdAndEmailResponse}
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
	 * @return FileSystemResource
	 */
	public FileSystemResource searchAttachmentByJobExecution(
		OptimizationConfigurationAttachmentRequest request);

	/**
	 * Searchs for all OptimizationJobExecutions available By the User Email.
	 *
	 * @param request
	 * 			{@link OptimizationJobExecutionsRequest}
	 *
	 * @return {@link OptimizationJobExecutionsResponse}
	 */
	public OptimizationJobExecutionsResponse searchOptimizationJobExecutionsByEmail(
		OptimizationJobExecutionsRequest request);

	/**
	 * Returns the Latex file associated with the Execution By ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return FileSystemResource
	 */
	public FileSystemResource searchLatexByExecutionId(Integer id);

	/**
	 * Returns the R file associated with the Execution By ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return FileSystemResource
	 */
	public FileSystemResource searchRByExecutionId(Integer id);

}
