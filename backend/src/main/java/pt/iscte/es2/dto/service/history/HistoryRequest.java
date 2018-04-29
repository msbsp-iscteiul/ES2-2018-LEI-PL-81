package pt.iscte.es2.dto.service.history;

/**
 * DTO History Request
 */
public class HistoryRequest {

	private String email;

	public HistoryRequest() {

	}

	public HistoryRequest(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
