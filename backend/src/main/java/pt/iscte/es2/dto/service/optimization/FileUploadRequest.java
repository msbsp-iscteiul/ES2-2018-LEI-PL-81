package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO FileUpload Request
 *
 * Contains a sessionId that is unique and comes from the browser session where the User is submitting the form.
 * Contains also a {@link MultipartFile} that contains a problem to be solved by the JMetal Framework
 */
public class FileUploadRequest {

	private String sessionId;
	private MultipartFile file;

	public FileUploadRequest() {

	}

	public FileUploadRequest(String sessionId, MultipartFile file) {
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
