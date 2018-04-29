package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.FileUploadEntity;

@Repository
public interface FileUploadDao extends JpaRepository<FileUploadEntity, Long> {

	/**
	 * Searches a FilePath of the Problem Uploaded of the current SessionId
	 *
	 * @param sessionId
	 * 			String
	 *
	 * @return String
	 */
	FileUploadEntity findBySessionId(String sessionId);
}
