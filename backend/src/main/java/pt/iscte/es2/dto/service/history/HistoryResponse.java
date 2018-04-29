package pt.iscte.es2.dto.service.history;

/**
 * DTO History Response
 */
public class HistoryResponse {

	private HistoryResult result;

	public HistoryResponse() {

	}

	public HistoryResponse(HistoryResult result) {
		this.result = result;
	}

	public HistoryResult getResult() {
		return result;
	}

	public void setResult(HistoryResult result) {
		this.result = result;
	}
}
