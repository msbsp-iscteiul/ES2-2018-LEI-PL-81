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
	 * 			{@link OptimizationConfiguration}
	 *
	 * @return {@link OptimizationConfiguration}
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
	 * @return {@link FileUpload}
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
	 * @return {@link OptimizationConfiguration}
	 */
	public OptimizationConfiguration searchOptimizationConfigurationByIdAndEmail(Integer id, String email);

	/**
	 * Searchs for all SummaryOptimizationConfiguration by Email
	 *
	 * @param email
	 * 			String
	 *
	 * @return List {@link SummaryOptimizationConfiguration}
	 */
	public List<SummaryOptimizationConfiguration> searchOptimizationConfigurationByEmail(String email);

	/**
	 * Persists an OptimizationConfiguration By ID
	 *
	 * @param optimizationConfiguration
	 * 			{@link OptimizationConfiguration}
	 *
	 * @return Integer
	 */
	public Integer saveExecutionOptimizationConfiguration(OptimizationConfiguration optimizationConfiguration);

	/**
	 * Searchs for an OptimizationJobExecutions By its ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return {@link OptimizationJobExecutions}
	 */
	public OptimizationJobExecutions searchOptimizationJobExecutionsById(Integer id);

	/**
	 * Persists a list of OptimizationJobSolutions
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param optimizationJobSolutions
	 * 			List
	 *
	 * @param state
	 * 			State
	 *
	 * @param latexPath
	 * 			Sring
	 *
	 * @param rPath
	 * 			String
	 *
	 * @return List {@link OptimizationJobSolutions}
	 */
	public List<OptimizationJobSolutions> saveOptimizationJobSolution(
		Integer id, List<OptimizationJobSolutions> optimizationJobSolutions, State state, String latexPath, String rPath);

	/**
	 * Updates the state of an Execution By ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @param state
	 * 			State
	 *
	 * @return {@link OptimizationJobExecutions}
	 */
	public OptimizationJobExecutions updateState(Integer id, State state);

	/**
	 * Searchs for all OptimizationJobExecutions available given an OptimizationConfigurationId
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return List {@link OptimizationJobExecutions}
	 */
	public List<OptimizationJobExecutions> searchOptimizationJobExecutionsByOptimizationConfigurationId(Integer id);

	/**
	 * Searchs for an OptimizationJobExecutions By ID
	 *
	 * @param optimizationJobExecutions
	 * 			{@link OptimizationJobExecutions}
	 *
	 * @return {@link OptimizationConfiguration}
	 */
	public OptimizationConfiguration searchOptimizationConfigurationByOptimizationJobExecution(
		OptimizationJobExecutions optimizationJobExecutions);

	/**
	 * Searchs a Latex Path given an Execution ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return String
	 */
	public String searchLatexPathByExecutionId(Integer id);

	/**
	 * Searchs a R Path given an Execution ID
	 *
	 * @param id
	 * 			Integer
	 *
	 * @return String
	 */
	public String searchRPathByExecutionId(Integer id);
}
