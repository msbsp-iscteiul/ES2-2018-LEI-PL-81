package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.business.UploadBusiness;
import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResponse;


@RestController
@RequestMapping(value = ApplicationConstants.UPLOAD_PATH)
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadBusiness uploadBusiness;

	/**
	 * @see UploadService#uploadFile(UploadRequest)
	 */
	@PostMapping(value = "/")
	public UploadResponse uploadFile(UploadRequest request) {
		UploadResponse response = new UploadResponse();
		response.setSessionId(request.getSessionId());
		response.setResult(uploadBusiness.uploadFile(request.getSessionId(), request.getFile()));
		return response;
	}
}
