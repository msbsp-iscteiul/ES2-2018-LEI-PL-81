package pt.iscte.es2.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.State;
import pt.iscte.es2.dto.service.optimization.*;

/**
 * @see OptimizationService
 */
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
				request.getId(), request.getState(), request.getSolutions(), request.getLatex(), request.getR()));
	}

	/**
	 * @see OptimizationService#updateState(Integer, State)
	 */
	@PostMapping(value = "/updateoptimizationjobexecution")
	public void updateState(Integer id, State state) {
		optimizationBusiness.updateState(id, state);
	}

	/**
	 * @see OptimizationService#searchAttachmentByJobExecution(OptimizationConfigurationAttachmentRequest)
	 */
	@JsonIgnore
	@PostMapping(value = "/searchattachmentbyjobexecution")
	public FileSystemResource searchAttachmentByJobExecution(
		OptimizationConfigurationAttachmentRequest request) {
		return optimizationBusiness.searchAttachmentByJobExecution(request.getId());
	}

	/**
	 * @see OptimizationService#searchOptimizationJobExecutionsByEmail(OptimizationJobExecutionsRequest)
	 */
	@PostMapping(value = "/searchoptimizationjobexecutionsbyemail")
	public OptimizationJobExecutionsResponse searchOptimizationJobExecutionsByEmail(OptimizationJobExecutionsRequest request) {
		return new OptimizationJobExecutionsResponse(
			optimizationBusiness.searchOptimizationJobExecutionsByEmail(request.getEmail()));
	}

	/**
	 * @see OptimizationService#searchLatexByExecutionId(Integer)
	 */
	@PostMapping(value = "/searchlatexbyexecutionid")
	public FileSystemResource searchLatexByExecutionId(Integer id) {
		return optimizationBusiness.searchLatexByExecutionId(id);
	}

	/**
	 * @see OptimizationService#searchRByExecutionId(Integer)
	 */
	@PostMapping(value = "/searchrbyexecutionid")
	public FileSystemResource searchRByExecutionId(Integer id) {
		return optimizationBusiness.searchRByExecutionId(id);
	}

}
