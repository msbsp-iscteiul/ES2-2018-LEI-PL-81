package pt.iscte.es2.dto;

import java.io.Serializable;

/**
 * DTO of the FileUpload
 */
public class FileUpload implements Serializable {

	private int id;
	private String sessionId;
	private String filePath;

	public FileUpload() {}

	public FileUpload(int id, String sessionId, String filePath) {
		this.id = id;
		this.sessionId = sessionId;
		this.filePath = filePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
