package pt.iscte.es2.datamanager;

import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.FileUpload;

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

}
