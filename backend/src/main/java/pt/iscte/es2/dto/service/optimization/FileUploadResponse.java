package pt.iscte.es2.dto.service.optimization;

/**
 * DTO FileUpload Response
 *
 * Contains the sessionId of the User and a {@link FileUploadResult}.
 */
public class FileUploadResponse {

	private String sessionId;
	private FileUploadResult result;

	public FileUploadResponse() {

	}

	public FileUploadResponse(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public FileUploadResult getResult() {
		return result;
	}

	public void setResult(FileUploadResult result) {
		this.result = result;
	}
}
