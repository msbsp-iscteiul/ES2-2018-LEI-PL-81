package pt.iscte.es2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.iscte.es2.ApplicationConstants;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.business.UploadBusiness;
import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResponse;

import java.io.File;

@RestController
@RequestMapping(value = ApplicationConstants.UPLOAD_PATH)
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadBusiness uploadBusiness;

	@PostMapping(value = "/")
	public UploadResponse uploadFile(UploadRequest request) {
		UploadResponse response = new UploadResponse();
		uploadBusiness.uploadFile(request.getSessionId(), request.getFile());
		return response;
	}
}
