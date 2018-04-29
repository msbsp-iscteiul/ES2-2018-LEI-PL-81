package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.HistoryBusiness;
import pt.iscte.es2.dto.service.history.HistoryRequest;
import pt.iscte.es2.dto.service.history.HistoryResponse;

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
