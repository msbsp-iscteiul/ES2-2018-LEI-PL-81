package pt.iscte.es2.datamanager;

import pt.iscte.es2.dto.UploadFile;

public interface UploadDatamanager {

	public UploadFile saveUploadFile(String sessionId, String filePath);

}
