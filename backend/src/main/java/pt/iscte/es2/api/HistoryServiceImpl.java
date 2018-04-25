package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.HistoryBusiness;
import pt.iscte.es2.business.UploadBusiness;
import pt.iscte.es2.dto.serviceview.history.HistoryRequest;
import pt.iscte.es2.dto.serviceview.history.HistoryResponse;
import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResponse;

@RestController
@RequestMapping(value = ApplicationConstants.HISTORY_PATH)
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryBusiness historyBusiness;

	/**
	 * @see HistoryService#searchOptimizationJobsByEmail(HistoryRequest)
	 */
	@CrossOrigin
	@GetMapping(value = "/")
	public HistoryResponse searchOptimizationJobsByEmail(HistoryRequest request) {
		return new HistoryResponse(historyBusiness.searchOptimizationJobsByEmail(request.getEmail()));
	}
}
