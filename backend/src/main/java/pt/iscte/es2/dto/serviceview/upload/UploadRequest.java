package pt.iscte.es2.dto.serviceview.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO Upload Request
 */
public class UploadRequest {

	private String sessionId;
	private MultipartFile file;

	public UploadRequest() {

	}

	public UploadRequest(String sessionId, MultipartFile file) {
		this.sessionId = sessionId;
		this.file = file;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
