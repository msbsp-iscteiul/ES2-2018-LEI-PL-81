package pt.iscte.es2.business;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.*;

import java.util.List;

public interface OptimizationBusiness {


	/**
	 * Persists the optimization suggested by the user which contains Variables, Objectives,
	 * AlgorithmChoiceMethod and CSV File
	 *
	 * @param problemName
	 * 			String
	 * @param description
	 * 			String
	 * @param email
	 * 			String
	 * @param sessionId
	 * 			String
	 * @param variables
	 * 			List
	 * @param objectives
	 * 			List
	 * @param algorithms
	 * 			List
	 * @param restrictions
	 * 			List
	 * @param algorithmChoiceMethod
	 * 			AlgorithmChoiceMethod
	 * @param executionMaxWaitTime
	 * 			Integer
	 * @param file
	 * 			MultipartFile
	 *
	 * 	@return SaveOptimizationConfigurationResult
	 */
	public SaveOptimizationConfigurationResult saveOptimization(
		String problemName,
		String description,
		String email,
		String sessionId,
		List<OptimizationConfigurationVariables> variables,
		List<OptimizationConfigurationObjectives> objectives,
		List<OptimizationConfigurationAlgorithms> algorithms,
		List<OptimizationConfigurationRestrictions> restrictions,
		AlgorithmChoiceMethod algorithmChoiceMethod,
		Integer executionMaxWaitTime,
		MultipartFile file);

	/**
	 * Searches for the filePath of the earlier submitted problem with the current sessionId.
	 *
	 * @param sessionId
	 * 			String
	 */
	public void searchFilePathBySessionId(String sessionId);


	/**
	 * Persists the Problem (file - jar) and sessionId of the user that wants the problem to be solved.
	 *
	 * @param sessionId
	 * 			String
	 * @param file
	 * 			MultipartFile
	 *
	 * @return {@link FileUploadResult}
	 */
	public FileUploadResult fileUpload(String sessionId, MultipartFile file);


	/**
	 * Searchs for an OptimizationConfiguration by an existing Id and Email
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param email
	 * 			String
	 *
	 * @return OptimizationConfigurationResult
	 */
	public OptimizationConfigurationResult searchOptimizationConfigurationByIdAndEmail(Integer id, String email);

	/**
	 * Searchs for a SummaryOptimizationConfigurationResult by Email
	 *
	 * @param email
	 * 			String
	 *
	 * @return SummaryOptimizationConfigurationResult
	 */
	public SummaryOptimizationConfigurationResult searchOptimizationConfigurationByEmail(String email);

	/**
	 * Executes an OptimizationConfiguration By ID and the User Email
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param email
	 * 			String
	 *
	 * @return ExecuteOptimizationConfigurationResult
	 */
	public ExecuteOptimizationConfigurationResult executeOptimizationConfiguration(Integer id, String email);

	/**
	 * Saves a list of OptimizationJobSolution By OptimizationJobExecution ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param state
	 * 			State
	 *
	 * @param solutions
	 * 			List
	 *
	 * @param latex
	 * 			MultipartFile
	 *
	 * @param r
	 * 			MultipartFile
	 *
	 * @return SaveOptimizationJobSolutionResult
	 */
	public SaveOptimizationJobSolutionResult saveOptimizationJobSolution(
		Integer id, State state, List<OptimizationJobSolutions> solutions, MultipartFile latex, MultipartFile r);


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
	 * @param id
	 * 			Integer
	 *
	 * @return OptimizationConfigurationAttachmentResult
	 */
	public OptimizationConfigurationAttachmentResult searchAttachmentByJobExecutionId(Integer id);

}
