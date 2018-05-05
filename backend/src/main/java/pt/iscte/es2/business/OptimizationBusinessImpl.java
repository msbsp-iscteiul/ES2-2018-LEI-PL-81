package pt.iscte.es2.business;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.FileUploadResult;
import pt.iscte.es2.dto.service.optimization.OptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SummaryOptimizationConfigurationResult;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class OptimizationBusinessImpl implements OptimizationBusiness {

	@Autowired
	private OptimizationDataManager optimizationDataManager;

	@Override
	public SaveOptimizationConfigurationResult saveOptimization(
		@RequestParam("problemName") String problemName,
		@RequestParam("description") String description,
		@RequestParam("email") String email,
		@RequestParam("sessionId") String sessionId,
		@RequestParam("variables") List<OptimizationConfigurationVariables> variables,
		@RequestParam("objectives") List<OptimizationConfigurationObjectives> objectives,
		@RequestParam("algorithms") List<OptimizationConfigurationAlgorithms> algorithms,
		@RequestParam("restrictions") List<OptimizationConfigurationRestrictions> restrictions,
		@RequestParam("algorithmChoiceMethod") AlgorithmChoiceMethod algorithmChoiceMethod,
		@RequestParam("executionMaxWaitTime") Integer executionMaxWaitTime,
		@RequestParam("file") MultipartFile file) {

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!file.isEmpty() && extension != null && !extension.equals("csv")) {
			return new SaveOptimizationConfigurationResult("File Extension is incorrect.");
		}

		StringBuilder stringBuilder = new StringBuilder();

		if (!file.isEmpty()) {
			createAndWriteFileToDirectory(ApplicationConstants.CSV_PATH, file);
			File fileToDelete = new File(ApplicationConstants.CSV_PATH + file.getOriginalFilename());
			String line = "";
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ApplicationConstants.CSV_PATH + file.getOriginalFilename()))) {
				while ((line = bufferedReader.readLine()) != null) {
					if (line.split("\\s+").length == objectives.size()) {
						stringBuilder.append(line + "\n");
					} else {
						return new SaveOptimizationConfigurationResult("Solution Quality doesn't match. Please review the CSV file.");
					}
				}
			} catch (IOException e) {
				fileToDelete.delete();
				e.printStackTrace();
			}
			// Delete the current CSV file after processing
			fileToDelete.delete();
		}

		String filePath = optimizationDataManager.searchFilePathBySessionId(sessionId);

		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setAlgorithmChoiceMethod(algorithmChoiceMethod);
		optimizationConfiguration.setAlgorithmsQuantity(algorithms.size());
		optimizationConfiguration.setObjectivesQuantity(objectives.size());
		optimizationConfiguration.setVariablesQuantity(variables.size());
		optimizationConfiguration.setRestrictionsQuantity(restrictions.size());
		optimizationConfiguration.setEmail(email);
		optimizationConfiguration.setFilePath(filePath);
		optimizationConfiguration.setAlgorithms(algorithms);
		optimizationConfiguration.setObjectives(objectives);
		optimizationConfiguration.setVariables(variables);
		optimizationConfiguration.setRestrictions(restrictions);
		optimizationConfiguration.setExecutionMaxWaitTime(executionMaxWaitTime);
		optimizationConfiguration.setProblemName(problemName);
		optimizationConfiguration.setDescription(description);
		if (stringBuilder.length() != 0) {
			optimizationConfiguration.setUserSolutions(
				Collections.singletonList(new OptimizationConfigurationUserSolutions(stringBuilder.toString())));
		}
		OptimizationConfiguration savedOptimizationConfiguration = optimizationDataManager.saveOptimization(optimizationConfiguration);
		SaveOptimizationConfigurationResult result = new SaveOptimizationConfigurationResult();
		if (savedOptimizationConfiguration.getId() != null) {
			result.setId(savedOptimizationConfiguration.getId());
			result.setMessage("Success");
		} else {
			result.setMessage("Error");
		}
		return result;
	}

	// TODO - Was testing the Search... To Be Removed...
	@Override
	public void searchFilePathBySessionId(@RequestParam("sessionId") String sessionId) {
		optimizationDataManager.searchFilePathBySessionId(sessionId);
	}

	/**
	 * @see OptimizationBusiness#fileUpload(String, MultipartFile)
	 */
	public FileUploadResult fileUpload(@RequestParam("sessionId") String sessionId, @RequestParam("file") MultipartFile file) {
		FileUploadResult result = new FileUploadResult();
		if (file.isEmpty()) {
			return null;
		}
		createAndWriteFileToDirectory(ApplicationConstants.JARS_PATH, file);
		String filePath = ApplicationConstants.JARS_PATH + file.getOriginalFilename();
		FileUpload fileUpload = optimizationDataManager.saveFileUpload(sessionId, filePath);
		if (fileUpload != null && fileUpload.getId() > 0) {
			try {
				Problem<Solution<?>> problem = new LoadClientJarProblem().loadProblemFromJar(filePath);
				AlgorithmFinder.AlgorithmFinderResult algorithmFinderResult = new AlgorithmFinder(problem).execute();
				result.setVariables(problem.getNumberOfVariables());
				result.setObjectives(problem.getNumberOfObjectives());
				result.setAlgorithms(algorithmFinderResult.getAlgorithms());
				result.setVariable_type(algorithmFinderResult.getSolutionTypeName());
			} catch (InstantiationException|IllegalAccessException|ClassNotFoundException|MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Creates a Directory if it doesn't exists and saves the file inside it.
	 *
	 * @param directory
	 * 			String
	 * @param file
	 * 			MultipartFile
	 */
	private void createAndWriteFileToDirectory(String directory, MultipartFile file) {
		try {
			Path path = Paths.get(directory);
			if (!Files.exists(path)) {
				new File(directory).mkdirs();
			}
			byte[] bytes = file.getBytes();
			path = Paths.get(directory + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see OptimizationBusiness#searchOptimizationConfigurationByIdAndEmail(Integer, String)
	 */
	public OptimizationConfigurationResult searchOptimizationConfigurationByIdAndEmail(
		@RequestParam("id") Integer id,
		@RequestParam("email") String email) {
		return new OptimizationConfigurationResult(
			optimizationDataManager.searchOptimizationConfigurationByIdAndEmail(id, email));
	}

	/**
	 * @see OptimizationBusiness#searchOptimizationConfigurationByEmail(String)
	 */
	public SummaryOptimizationConfigurationResult searchOptimizationConfigurationByEmail(
		@RequestParam("email") String email) {
		return new SummaryOptimizationConfigurationResult(
			optimizationDataManager.searchOptimizationConfigurationByEmail(email));
	}


}
