package pt.iscte.es2.api;

import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResponse;

/**
 * Upload Service with the purpose of Upload Jar Files which consist on problems to be optimized.
 */
public interface UploadService {
	/**
	 * Uploads a Jar file that contains a problem to be optimized and returns
	 * a result that contains all the necessary data such as NumberOfVariables, NumberOfObjectives.
	 *
	 * @param request
	 *
	 * @return {@link UploadResponse}
	 */
	public UploadResponse uploadFile(UploadRequest request);
}
