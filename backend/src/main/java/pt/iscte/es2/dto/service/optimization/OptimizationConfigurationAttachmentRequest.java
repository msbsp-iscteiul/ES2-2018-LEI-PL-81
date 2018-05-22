package pt.iscte.es2.dto.service.optimization;

import pt.iscte.es2.dto.OptimizationConfiguration;

/**
 * DTO OptimizationConfiguration Attachment Request
 *
 * Request that contains an id of an {@link OptimizationConfiguration} to return the Attachment associated
 * with the {@link OptimizationConfiguration} submitted earlier.
 */
public class OptimizationConfigurationAttachmentRequest {

	private Integer id;

	public OptimizationConfigurationAttachmentRequest() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
