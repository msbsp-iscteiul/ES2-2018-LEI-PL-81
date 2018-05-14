package pt.iscte.es2.datamanager;

import pt.iscte.es2.dto.*;

import java.util.List;

public interface OptimizationDataManager {

	/**
	 * Searches an existing FilePath given a sessionId
	 *
	 * @param sessionId
	 * 			String
	 *
	 * @return String
	 */
	public String searchFilePathBySessionId(String sessionId);

	/**
	 * Persists the given OptimizationConfiguration
	 *
	 * @param optimizationConfiguration
	 * 			OptimizationConfiguration
	 */
	public OptimizationConfiguration saveOptimization(OptimizationConfiguration optimizationConfiguration);

	/**
	 * Persists the path of the uploaded problem with the associated sessionId
	 *
	 * @param sessionId
	 * 			String
	 *
	 * @param filePath
	 * 			String
	 *
	 * @return FileUpload
	 */
	public FileUpload saveFileUpload(String sessionId, String filePath);

	/**
	 * Searchs for an OptimizationConfiguration by an existing Id and Email
	 *
	 * @param id
	 * 			Integer
	 * @param email
	 * 			String
	 *
	 * @return OptimizationConfiguration
	 */
	public OptimizationConfiguration searchOptimizationConfigurationByIdAndEmail(Integer id, String email);

	/**
	 * Searchs for all SummaryOptimizationConfiguration by Email
	 *
	 * @param email
	 * 			String
	 *
	 * @return List<SummaryOptimizationConfiguration>
	 */
	public List<SummaryOptimizationConfiguration> searchOptimizationConfigurationByEmail(String email);

	/**
	 * Persists an OptimizationConfiguration By ID
	 *
	 * @param optimizationConfiguration
	 * 			OptimizationConfiguration
	 *
	 * @return OptimizationJobExecutions
	 */
	public OptimizationJobExecutions saveExecutionOptimizationConfiguration(OptimizationConfiguration optimizationConfiguration);

	/**
	 * Searchs for an OptimizationJobExecutions By its ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return OptimizationJobExecutions
	 */
	public OptimizationJobExecutions searchOptimizationJobExecutionsById(Integer id);

	/**
	 * Persists a list of OptimizationJobSolutions
	 *
	 * @param optimizationJobSolutions
	 * 			List<OptimizationJobSolutions>
	 *
	 * @return OptimizationJobExecutions
	 */
	public List<OptimizationJobSolutions> saveOptimizationJobSolution(List<OptimizationJobSolutions> optimizationJobSolutions);
}
