package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.*;

@RestController
@RequestMapping(value = ApplicationConstants.OPTIMIZATION_PATH)
public class OptimizationServiceImpl implements OptimizationService {

	@Autowired
	private OptimizationBusiness optimizationBusiness;

	/**
	 * @see OptimizationService#saveOptimization(SaveOptimizationConfigurationRequest)
	 */
	@PostMapping(value = "/save")
	public SaveOptimizationConfigurationResponse saveOptimization(SaveOptimizationConfigurationRequest request) {
		return new SaveOptimizationConfigurationResponse(optimizationBusiness.saveOptimization(
			request.getProblemName(),
			request.getDescription(),
			request.getEmail(), request.getSessionId(), request.getVariables(),
			request.getObjectives(), request.getAlgorithms(), request.getRestrictions(),
			request.getAlgorithmChoiceMethod(), request.getExecutionMaxWaitTime(), request.getFile()));
	}

	@PostMapping(value = "/searchfilepathbysessionid")
	public void searchFilePathBySessionId(String sessionId) {
		optimizationBusiness.searchFilePathBySessionId(sessionId);
	}

	/**
	 * @see OptimizationService#fileUpload(FileUploadRequest)
	 */
	@PostMapping(value = "/fileupload")
	public FileUploadResponse fileUpload(FileUploadRequest request) {
		FileUploadResponse response = new FileUploadResponse(request.getSessionId());
		response.setResult(optimizationBusiness.fileUpload(request.getSessionId(), request.getFile()));
		return response;
	}

	/**
	 * @see OptimizationService#searchOptimizationConfigurationByIdAndEmail(SearchOptimizationConfigurationByIdAndEmailRequest)
	 */
	@PostMapping(value = "/searchoptimizationconfigurationbyidandemail")
	public SearchOptimizationConfigurationByIdAndEmailResponse searchOptimizationConfigurationByIdAndEmail(
		SearchOptimizationConfigurationByIdAndEmailRequest request) {
		return new SearchOptimizationConfigurationByIdAndEmailResponse(
			optimizationBusiness.searchOptimizationConfigurationByIdAndEmail(request.getId(), request.getEmail()));
	}

	/**
	 * @see OptimizationService#searchOptimizationConfigurationByEmail(SearchOptimizationConfigurationByEmailRequest)
	 */
	@CrossOrigin
	@GetMapping(value = "/searchoptimizationconfigurationbyemail")
	public SearchOptimizationConfigurationByEmailResponse searchOptimizationConfigurationByEmail(
		SearchOptimizationConfigurationByEmailRequest request) {
		return new SearchOptimizationConfigurationByEmailResponse(
			optimizationBusiness.searchOptimizationConfigurationByEmail(request.getEmail()));
	}

	/**
	 * @see OptimizationService#executeOptimizationConfiguration(ExecuteOptimizationConfigurationRequest)
	 */
	@PostMapping(value = "/executeoptimizationconfiguration")
	public ExecuteOptimizationConfigurationResponse executeOptimizationConfiguration(
		ExecuteOptimizationConfigurationRequest request) {
		return new ExecuteOptimizationConfigurationResponse(
			optimizationBusiness.executeOptimizationConfiguration(request.getId(), request.getEmail()));
	}

	/**
	 * @see OptimizationService#saveOptimizationJobSolution(SaveOptimizationJobSolutionRequest)
	 */
	@PostMapping(value = "/saveoptimizationjobsolution")
	public SaveOptimizationJobSolutionResponse saveOptimizationJobSolution(SaveOptimizationJobSolutionRequest request) {
		return new SaveOptimizationJobSolutionResponse(
			optimizationBusiness.saveOptimizationJobSolution(
				request.getId(), request.getSolutions(), request.getLatex(), request.getR()));
	}



}
