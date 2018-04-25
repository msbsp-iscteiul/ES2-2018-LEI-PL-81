package pt.iscte.es2.business;

import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.dto.serviceview.history.HistoryResult;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

public interface HistoryBusiness {
	/**
	 *
	 *
	 * @return {@link HistoryResult}
	 */
	public HistoryResult searchOptimizationJobsByEmail(String email);
}
