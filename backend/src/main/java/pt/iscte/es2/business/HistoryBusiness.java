package pt.iscte.es2.business;

import pt.iscte.es2.dto.service.history.HistoryResult;

public interface HistoryBusiness {

	/**
	 * @return {@link HistoryResult}
	 */
	public HistoryResult searchOptimizationJobsByEmail(String email);
}
