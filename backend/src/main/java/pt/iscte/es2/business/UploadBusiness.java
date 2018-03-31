package pt.iscte.es2.business;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

public interface UploadBusiness {
	public UploadResult uploadFile(String sessionId, MultipartFile file);
}
