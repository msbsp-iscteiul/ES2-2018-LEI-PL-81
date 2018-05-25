package pt.iscte.es2.api;

import org.springframework.core.io.FileSystemResource;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.*;

/**
 * OptimizationConfiguration Service API
 *
 * This service will provide all the incoming requests associated with all related {@link OptimizationConfiguration}
 * operations, delivering the appropriate responses.
 */
public interface OptimizationService {

	/**
	 * Persists an {@link OptimizationConfiguration} suggested by the user.
	 *
	 * @param request
	 * 			{@link SaveOptimizationConfigurationRequest}
	 *
	 * @return {@link SaveOptimizationConfigurationResponse}
	 */
	public SaveOptimizationConfigurationResponse saveOptimization(SaveOptimizationConfigurationRequest request);

	/**
	 * Uploads a Jar file that contains a problem to be solved, returning all necessary
	 * data such as NumberOfVariables, NumberOfObjectives.
	 *
	 * @param request
	 * 			{@link FileUploadRequest}
	 *
	 * @return {@link FileUploadResponse}
	 */
	public FileUploadResponse fileUpload(FileUploadRequest request);

	/**
	 * Searchs for an {@link OptimizationConfiguration} by id and user email.
	 *
	 * @param request
	 * 			{@link SearchOptimizationConfigurationByIdAndEmailRequest}
	 *
	 * @return {@link SearchOptimizationConfigurationByIdAndEmailResponse}
	 */
	public SearchOptimizationConfigurationByIdAndEmailResponse searchOptimizationConfigurationByIdAndEmail(
		SearchOptimizationConfigurationByIdAndEmailRequest request);

	/**
	 * Searchs for all {@link SummaryOptimizationConfiguration} by email.
	 *
	 * @param request
	 * 			{@link SearchOptimizationConfigurationByEmailRequest}
	 *
	 * @return {@link SearchOptimizationConfigurationByEmailResponse}
	 */
	public SearchOptimizationConfigurationByEmailResponse searchOptimizationConfigurationByEmail(
		SearchOptimizationConfigurationByEmailRequest request);

	/**
	 * Executes an {@link OptimizationConfiguration} by id and user email.
	 *
	 * @param request
	 * 			{@link ExecuteOptimizationConfigurationRequest}
	 *
	 * @return {@link ExecuteOptimizationConfigurationResponse}
	 */
	public ExecuteOptimizationConfigurationResponse executeOptimizationConfiguration(
		ExecuteOptimizationConfigurationRequest request);

	/**
	 * Persists a list of {@link OptimizationJobSolutions} given an {@link OptimizationJobExecutions} id.
	 *
	 * @param request
	 * 			{@link SaveOptimizationJobSolutionRequest}
	 *
	 * @return {@link SaveOptimizationJobSolutionResponse}
	 */
	public SaveOptimizationJobSolutionResponse saveOptimizationJobSolution(SaveOptimizationJobSolutionRequest request);

	/**
	 * Updates the State of an {@link OptimizationJobExecutions} by id.
	 *
	 * @param id - ID of the Execution
	 * 			Integer
	 *
	 * @param state - State of the Execution
	 * 			{@link State}
	 */
	public void updateState(Integer id, State state);

	/**
	 * Returns the Problem (jar file) associated with an {@link OptimizationJobExecutions} by id.
	 *
	 * @param request
	 * 			{@link OptimizationConfigurationAttachmentRequest}
	 *
	 * @return FileSystemResource
	 */
	public FileSystemResource searchAttachmentByJobExecution(OptimizationConfigurationAttachmentRequest request);

	/**
	 * Searchs for all {@link OptimizationJobExecutions} available by user email.
	 *
	 * @param request
	 * 			{@link OptimizationJobExecutionsRequest}
	 *
	 * @return {@link OptimizationJobExecutionsResponse}
	 */
	public OptimizationJobExecutionsResponse searchOptimizationJobExecutionsByEmail(
		OptimizationJobExecutionsRequest request);

	/**
	 * Returns the Latex file associated with {@link OptimizationJobExecutions} by id.
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return FileSystemResource
	 */
	public FileSystemResource searchLatexByExecutionId(Integer id);

	/**
	 * Returns the R file associated with the {@link OptimizationJobExecutions} by id.
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return FileSystemResource
	 */
	public FileSystemResource searchRByExecutionId(Integer id);

}
