package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.FileUploadRequest;
import pt.iscte.es2.dto.service.optimization.FileUploadResponse;
import pt.iscte.es2.dto.service.optimization.OptimizationRequest;
import pt.iscte.es2.dto.service.optimization.OptimizationResponse;

@RestController
@RequestMapping(value = ApplicationConstants.OPTIMIZATION_PATH)
public class OptimizationServiceImpl implements OptimizationService {

	@Autowired
	private OptimizationBusiness optimizationBusiness;

	/**
	 * @see OptimizationService#saveOptimization(OptimizationRequest)
	 */
	@PostMapping(value = "/save")
	public OptimizationResponse saveOptimization(OptimizationRequest request) {
		return new OptimizationResponse(optimizationBusiness.saveOptimization(
			request.getProblemName(),
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

}
