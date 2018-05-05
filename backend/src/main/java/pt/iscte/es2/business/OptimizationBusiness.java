package pt.iscte.es2.business;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.FileUploadResult;
import pt.iscte.es2.dto.service.optimization.OptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SummaryOptimizationConfigurationResult;

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
	 * 			List<OptimizationConfigurationVariables>
	 * @param objectives
	 * 			List<OptimizationConfigurationObjectives>
	 * @param algorithms
	 * 			List<OptimizationConfigurationAlgorithms>
	 * @param restrictions
	 * 			List<OptimizationConfigurationRestrictions>
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
}
