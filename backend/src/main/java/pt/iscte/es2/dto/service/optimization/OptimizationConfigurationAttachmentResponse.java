package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;

public class OptimizationConfigurationAttachmentResponse {

	private MultipartFile file;

	public OptimizationConfigurationAttachmentResponse() {

	}

	public OptimizationConfigurationAttachmentResponse(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
