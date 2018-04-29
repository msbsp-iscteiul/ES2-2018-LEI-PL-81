package pt.iscte.es2.api;

import pt.iscte.es2.dto.service.history.HistoryRequest;
import pt.iscte.es2.dto.service.history.HistoryResponse;

public interface HistoryService {

	/**
	 * @return {@link HistoryResponse}
	 */
	public HistoryResponse searchOptimizationJobsByEmail(HistoryRequest request);
}
