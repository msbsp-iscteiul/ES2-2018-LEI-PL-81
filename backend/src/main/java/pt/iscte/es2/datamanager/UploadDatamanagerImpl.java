package pt.iscte.es2.datamanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.iscte.es2.dao.UploadDao;
import pt.iscte.es2.jpa.UploadEntity;

import javax.transaction.Transactional;

@Component
@Transactional
public class UploadDatamanagerImpl implements UploadDatamanager {

	@Autowired
	private UploadDao uploadDao;

	public void saveUploadFile(String sessionId, String filePath) {
		UploadEntity entity = new UploadEntity();
		entity.setSessionId(sessionId);
		entity.setFilePath(filePath);
		uploadDao.save(entity);
	}

}
