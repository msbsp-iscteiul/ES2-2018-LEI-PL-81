package pt.iscte.es2.business;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.Sender;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.*;

import javax.transaction.Transactional;
import java.io.*;
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

	@Autowired
	private Sender sender;

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

		StringBuilder stringBuilder = new StringBuilder();
		if (file != null) {
			if (file.isEmpty()) {
				return new SaveOptimizationConfigurationResult("The File is Empty!");
			}

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			if (extension != null && !extension.equals("csv")) {
				return new SaveOptimizationConfigurationResult("Incorrect Extension");
			}

			if (!file.isEmpty()) {
				createAndWriteFileToDirectory(ApplicationConstants.CSV_PATH, file);
				File fileToDelete = new File(ApplicationConstants.CSV_PATH + file.getOriginalFilename());
				String line = "";
				try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ApplicationConstants.CSV_PATH + file.getOriginalFilename()))) {
					while ((line = bufferedReader.readLine()) != null) {
						if (line.split("\\s+").length == objectives.size()) {
							stringBuilder.append(line + "\n");
						} else {
							return new SaveOptimizationConfigurationResult("Something went wrong Saving the CSV File");
						}
					}
				} catch (IOException e) {
					fileToDelete.delete();
					e.printStackTrace();
				}
				// Delete the current CSV file after processing
				fileToDelete.delete();
			}
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
		if (savedOptimizationConfiguration.getId() != null) {
			return new SaveOptimizationConfigurationResult(savedOptimizationConfiguration.getId());
		} else {
			return new SaveOptimizationConfigurationResult("Failed to submit the Optimization Configuration, please try again later.");
		}
	}

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

	/**
	 * @see OptimizationBusiness#executeOptimizationConfiguration(Integer, String)
	 */
	public ExecuteOptimizationConfigurationResult executeOptimizationConfiguration(
		@RequestParam("id") Integer id, @RequestParam("email") String email) {
		ExecuteOptimizationConfigurationResult result;
		OptimizationConfiguration optimizationConfiguration = optimizationDataManager
			.searchOptimizationConfigurationByIdAndEmail(id, email);
		if (optimizationConfiguration != null) {
			result = new ExecuteOptimizationConfigurationResult(optimizationDataManager
				.saveExecutionOptimizationConfiguration(optimizationConfiguration), "Success");
			sender.sendMessage(result.getOptimizationJobExecution().getId());
		} else {
			result = new ExecuteOptimizationConfigurationResult(null, "Fail");
		}
		return result;
	}

	/**
	 * @see OptimizationBusiness#saveOptimizationJobSolution(Integer, State, List, MultipartFile, MultipartFile)
	 */
	public SaveOptimizationJobSolutionResult saveOptimizationJobSolution(
		@RequestParam("id") Integer id, @RequestParam("state") State state,
		@RequestParam("solutions") List<OptimizationJobSolutions> solutions,
		@RequestParam("latex") MultipartFile latex, @RequestParam("r") MultipartFile r) {

		if (state.equals(State.Failed)) {
			optimizationDataManager.updateState(id, state);
			return new SaveOptimizationJobSolutionResult("FAILED");
		} else if (state.equals(State.Finished)) {
			if (latex != null) {
				if (latex.isEmpty()) {
					return new SaveOptimizationJobSolutionResult("Latex File is Empty!");
				}
				String extension = FilenameUtils.getExtension(latex.getOriginalFilename());
				if (extension != null && !extension.equals("pdf")) {
					return new SaveOptimizationJobSolutionResult("Wrong Format");
				}
				createAndWriteFileToDirectory(ApplicationConstants.LATEX_PATH, latex);
			}
			if (r != null) {
				if (r.isEmpty()) {
					return new SaveOptimizationJobSolutionResult("R File is Empty!");
				}
				String extension = FilenameUtils.getExtension(r.getOriginalFilename());
				if (extension != null && !extension.equals("eps")) {
					return new SaveOptimizationJobSolutionResult("Wrong Format");
				}
				createAndWriteFileToDirectory(ApplicationConstants.R_PATH, r);
			}
			OptimizationJobExecutions optimizationJobExecutions = optimizationDataManager.searchOptimizationJobExecutionsById(id);
			solutions.forEach(solution -> solution.setOptimizationJobExecutions(optimizationJobExecutions));
			List<OptimizationJobSolutions> savedSolutions = optimizationDataManager.saveOptimizationJobSolution(solutions);
			if (!savedSolutions.isEmpty()) {
				OptimizationJobExecutions execution = optimizationDataManager.updateState(id, state);
				return new SaveOptimizationJobSolutionResult("SUCCESS");
			}
		}
		return new SaveOptimizationJobSolutionResult("NO SUCCESS");
	}

	/**
	 * @see OptimizationBusiness#searchAttachmentByJobExecution(Integer)
	 */
	public OptimizationConfigurationAttachmentResult searchAttachmentByJobExecution(Integer id) {
		OptimizationJobExecutions optimizationJobExecution = optimizationDataManager
			.searchOptimizationJobExecutionsById(id);
		MultipartFile file = null;
		if (optimizationJobExecution != null) {
			File tmpFile = new File(optimizationJobExecution.getOptimizationConfiguration().getFilePath());
			FileInputStream input;
			try {
				input = new FileInputStream(tmpFile);
				file = new MockMultipartFile("file",
					tmpFile.getName(), MediaType.MULTIPART_FORM_DATA_VALUE, IOUtils.toByteArray(input));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new OptimizationConfigurationAttachmentResult(file);
	}

	/**
	 * @see OptimizationBusiness#updateState(Integer, State)
	 */
	public void updateState(@RequestParam("id") Integer id, @RequestParam("state") State state) {
		optimizationDataManager.updateState(id, state);
	}
}
