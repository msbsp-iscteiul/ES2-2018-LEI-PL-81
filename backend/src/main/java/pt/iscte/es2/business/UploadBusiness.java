package pt.iscte.es2.business;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

public interface UploadBusiness {
	/**
	 * Persists the Problem (file - jar) and sessionId of the user that wants the problem to be solved.
	 *
	 * @param sessionId
	 * @param file
	 *
	 * @return {@link UploadResult}
	 */
	public UploadResult uploadFile(String sessionId, MultipartFile file);
}
