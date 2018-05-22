package pt.iscte.es2.dto.service.optimization;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO OptimizationConfiguration Attachment Result
 *
 * Result that contains the Attachment (Problem submitted earlier by the user)
 */
public class OptimizationConfigurationAttachmentResult {

	private MultipartFile file;

	public OptimizationConfigurationAttachmentResult() {

	}

	public OptimizationConfigurationAttachmentResult(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
