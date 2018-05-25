package pt.iscte.es2.business;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
import java.util.ArrayList;
import java.util.Comparator;
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

		List<OptimizationConfigurationUserSolutions> solutions = new ArrayList<>();
		if (file != null) {
			if (file.isEmpty()) {
				return new SaveOptimizationConfigurationResult("The File is Empty!");
			}
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			if (extension != null && !extension.equals("csv")) {
				return new SaveOptimizationConfigurationResult("Incorrect Extension");
			}
			if (!file.isEmpty()) {
				processCSV(file, objectives.size(), solutions);
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
		optimizationConfiguration.setVariables(variables);
		optimizationConfiguration.setObjectives(objectives);
		optimizationConfiguration.setRestrictions(restrictions);
		optimizationConfiguration.setAlgorithms(algorithms);
		optimizationConfiguration.setUserSolutions(solutions);
		optimizationConfiguration.setExecutionMaxWaitTime(executionMaxWaitTime);
		optimizationConfiguration.setProblemName(problemName);
		optimizationConfiguration.setDescription(description);
		OptimizationConfiguration savedOptimizationConfiguration = optimizationDataManager.saveOptimization(optimizationConfiguration);
		if (savedOptimizationConfiguration.getId() != null) {
			return new SaveOptimizationConfigurationResult(savedOptimizationConfiguration.getId());
		} else {
			return new SaveOptimizationConfigurationResult("Failed to submit the Optimization Configuration, please try again later.");
		}
	}

	private void processCSV(MultipartFile file, Integer size, List<OptimizationConfigurationUserSolutions> solutions) {
		File newFile = null;
		try {
			newFile = File.createTempFile(ApplicationConstants.CSV_PATH, file.getOriginalFilename());
			file.transferTo(newFile);
		} catch (IOException e) {
			e.printStackTrace();
			newFile.delete();
			// return null;
		}
		createAndWriteFileToDirectory(ApplicationConstants.CSV_PATH, newFile);
		File fileToDelete = new File(ApplicationConstants.CSV_PATH + newFile.getName());
		String line = "";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(newFile.getPath()))) {
			while ((line = bufferedReader.readLine()) != null) {
				if (line.split("\\s+").length == size) {
					OptimizationConfigurationUserSolutions userSolutions = new OptimizationConfigurationUserSolutions();
					userSolutions.setSolutionQuality(line);
					solutions.add(userSolutions);
				} else {
					fileToDelete.delete();
					// return new SaveOptimizationConfigurationResult("The User Solutions must match the Number of Objectives");
				}
			}
		} catch (IOException e) {
			fileToDelete.delete();
			e.printStackTrace();
		}
		// Delete the current CSV file after processing
		fileToDelete.delete();
	}

	/**
	 * @see OptimizationBusiness#fileUpload(String, MultipartFile)
	 */
	public FileUploadResult fileUpload(@RequestParam("sessionId") String sessionId, @RequestParam("file") MultipartFile file) {
		FileUploadResult result = new FileUploadResult();
		if (file != null) {
			if (file.isEmpty()) {
				return null;
			}
			File newFile;
			try {
				newFile = File.createTempFile(ApplicationConstants.JARS_PATH, file.getOriginalFilename());
				file.transferTo(newFile);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			createAndWriteFileToDirectory(ApplicationConstants.JARS_PATH, newFile);
			String filePath = ApplicationConstants.JARS_PATH + newFile.getName();
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
		}
		return result;
	}

	/**
	 * Creates a Directory if it doesn't exists and saves the file inside it.
	 *
	 * @param directory - Directory where the file will be saved
	 * 			{@link String}
	 * @param file - File to be saved in the given Directory
	 * 			{@link File}
	 */
	private void createAndWriteFileToDirectory(String directory, File file) {
		try {
			Path path = Paths.get(directory);
			if (!Files.exists(path)) {
				new File(directory).mkdirs();
			}
			byte[] bytes = Files.readAllBytes(file.toPath());
			path = Paths.get(directory + file.getName());
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
		List<SummaryOptimizationConfiguration> summaries = optimizationDataManager
			.searchOptimizationConfigurationByEmail(email);
		summaries.sort(Comparator.comparing(SummaryOptimizationConfiguration::getId).reversed());
		return new SummaryOptimizationConfigurationResult(summaries);
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
				.saveExecutionOptimizationConfiguration(optimizationConfiguration), "SUCCESS");
			Object[] idEmail = new Object[]{id.longValue(), result.getId().longValue(), email};
			sender.sendMessage(idEmail);
		} else {
			result = new ExecuteOptimizationConfigurationResult(null, "FAILED");
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
			String latexPath = "";
			String rPath = "";
			if (latex != null) {
				if (latex.isEmpty()) {
					return new SaveOptimizationJobSolutionResult("Latex File is Empty!");
				}
				String extension = FilenameUtils.getExtension(latex.getOriginalFilename());
				if (extension != null && !extension.equals("pdf")) {
					return new SaveOptimizationJobSolutionResult("Wrong Format");
				}
				File newFile;
				try {
					newFile = File.createTempFile(ApplicationConstants.LATEX_PATH, latex.getOriginalFilename());
					latex.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
					return new SaveOptimizationJobSolutionResult("Something went wrong reading from the file");
				}
				createAndWriteFileToDirectory(ApplicationConstants.LATEX_PATH, newFile);
				latexPath = ApplicationConstants.LATEX_PATH + newFile.getName();
			}
			if (r != null) {
				if (r.isEmpty()) {
					return new SaveOptimizationJobSolutionResult("R File is Empty!");
				}
				String extension = FilenameUtils.getExtension(r.getOriginalFilename());
				if (extension != null && !extension.equals("eps")) {
					return new SaveOptimizationJobSolutionResult("Wrong Format");
				}
				File newFile;
				try {
					newFile = File.createTempFile(ApplicationConstants.R_PATH, r.getOriginalFilename());
					r.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
					return new SaveOptimizationJobSolutionResult("Something went wrong reading from the file");
				}
				createAndWriteFileToDirectory(ApplicationConstants.R_PATH, newFile);
				rPath = ApplicationConstants.R_PATH + newFile.getName();
			}
			return new SaveOptimizationJobSolutionResult("SUCCESS", optimizationDataManager
				.saveOptimizationJobSolution(id, solutions, state, latexPath, rPath));
		}
		return new SaveOptimizationJobSolutionResult("NO SUCCESS");
	}

	/**
	 * @see OptimizationBusiness#searchAttachmentByJobExecution(Integer)
	 */
	public FileSystemResource searchAttachmentByJobExecution(Integer id) {
		OptimizationJobExecutions optimizationJobExecution = optimizationDataManager
			.searchOptimizationJobExecutionsById(id);
		OptimizationConfiguration optimizationConfiguration = optimizationDataManager
			.searchOptimizationConfigurationByOptimizationJobExecution(optimizationJobExecution);
		if (optimizationConfiguration != null) {
			return new FileSystemResource(new File(optimizationConfiguration.getFilePath()));
		}
		return null;
	}

	/**
	 * @see OptimizationBusiness#updateState(Integer, State)
	 */
	public void updateState(@RequestParam("id") Integer id, @RequestParam("state") State state) {
		optimizationDataManager.updateState(id, state);
	}

	/**
	 * @see OptimizationBusiness#searchOptimizationJobExecutionsByEmail(String)
	 */
	public OptimizationJobExecutionsResult searchOptimizationJobExecutionsByEmail(@RequestParam("email") String email) {
		OptimizationJobExecutionsResult result = new OptimizationJobExecutionsResult();
		List<SummaryOptimizationConfiguration> optimizationConfigurationList = optimizationDataManager
			.searchOptimizationConfigurationByEmail(email);
		List<OptimizationJobExecutionSummary> summaries = new ArrayList<>();
		optimizationConfigurationList
			.forEach(optimizationConfiguration -> {
				optimizationDataManager
					.searchOptimizationJobExecutionsByOptimizationConfigurationId(optimizationConfiguration.getId())
					.forEach(execution -> {
						OptimizationJobExecutionSummary summary = new OptimizationJobExecutionSummary();
						summary.setExecutionId(execution.getId());
						summary.setOptimizationConfigurationId(optimizationConfiguration.getId());
						summary.setProblemName(optimizationConfiguration.getProblemName());
						summary.setStartDate(execution.getStartDate());
						summary.setEndDate(execution.getEndDate());
						summary.setState(execution.getState().toString());
						summaries.add(summary);
					});
			});
		summaries.sort(Comparator.comparing(OptimizationJobExecutionSummary::getExecutionId).reversed());
		result.getExecutions().addAll(summaries);
		return result;
	}

	/**
	 * @see OptimizationBusiness#searchLatexByExecutionId(Integer)
	 */
	public FileSystemResource searchLatexByExecutionId(Integer id) {
		String latexPath = optimizationDataManager.searchLatexPathByExecutionId(id);
		if (latexPath != null) {
			return new FileSystemResource(new File(latexPath));
		}
		return null;
	}

	/**
	 * @see OptimizationBusiness#searchRByExecutionId(Integer)
	 */
	public FileSystemResource searchRByExecutionId(Integer id) {
		String rPath = optimizationDataManager.searchRPathByExecutionId(id);
		if (rPath != null) {
			return new FileSystemResource(new File(rPath));
		}
		return null;
	}
}
