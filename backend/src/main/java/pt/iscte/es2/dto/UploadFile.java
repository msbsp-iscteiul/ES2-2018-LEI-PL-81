package pt.iscte.es2.dto;

import java.io.Serializable;

public class UploadFile implements Serializable {
	private int id;
	private String sessionId;
	private String filePath;

	public UploadFile() {}

	public UploadFile(int id, String sessionId, String filePath) {
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
