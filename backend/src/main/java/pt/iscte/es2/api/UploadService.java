package pt.iscte.es2.api;

import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResponse;

public interface UploadService {
	public UploadResponse uploadFile(UploadRequest request);
}
