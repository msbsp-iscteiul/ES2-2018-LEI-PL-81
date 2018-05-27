package pt.iscte.es2.dto;

import java.io.Serializable;

/**
 * DTO FileUpload
 *
 * Contains the id of the persisted file uploaded, sessionId of the user that send the Jar Problem (File)
 * and the filePath of where the file is located.
 */
public class FileUpload implements Serializable {

	private Integer id;
	private String sessionId;
	private String filePath;

	public FileUpload() {}

	public FileUpload(int id, String sessionId, String filePath) {
		this.id = id;
		this.sessionId = sessionId;
		this.filePath = filePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
