package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;

/**
 * DTO OptimizationConfiguration Attachment Response
 *
 * Response that contains an {@link OptimizationConfigurationAttachmentResult}
 */
public class OptimizationConfigurationAttachmentResponse {

	private OptimizationConfigurationAttachmentResult result;

	public OptimizationConfigurationAttachmentResponse() {

	}

	public OptimizationConfigurationAttachmentResponse(OptimizationConfigurationAttachmentResult result) {
		this.result = result;
	}

	public OptimizationConfigurationAttachmentResult getResult() {
		return result;
	}

	public void setResult(OptimizationConfigurationAttachmentResult result) {
		this.result = result;
	}
}
