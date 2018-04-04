package pt.iscte.es2.dto.serviceview.upload;

/**
 * DTO Upload Response
 */
public class UploadResponse {

	private String sessionId;
	private UploadResult result;

	public UploadResponse() {

	}

	public UploadResponse(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UploadResult getResult() {
		return result;
	}

	public void setResult(UploadResult result) {
		this.result = result;
	}
}
