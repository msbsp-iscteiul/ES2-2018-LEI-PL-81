package pt.iscte.es2.datamanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.dao.FileUploadDao;
import pt.iscte.es2.dto.FileUpload;
import pt.iscte.es2.jpa.FileUploadEntity;

/**
 * Tests for the OptimizationConfiguration Datamanager when saving a File (JAR problem)
 */
public class SaveFileUploadDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private FileUploadDao fileUploadDao;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Should be successful when saving a jar problem file path with session id
	 */
	@Test
	public void successfulWhenSavingAFileUploadPathWithSessionId() {
		FileUploadEntity entity = getFileUploadEntity();
		Mockito.when(fileUploadDao.saveAndFlush(Mockito.any())).thenReturn(entity);
		FileUpload fileUpload = optimizationDataManager.saveFileUpload("sessionId", "/path/of/problem");
		Assert.assertNotNull(fileUpload);
		Assert.assertEquals(new Integer(1), fileUpload.getId());
		Assert.assertEquals("/path/of/problem", fileUpload.getFilePath());
		Assert.assertEquals("sessionId", fileUpload.getSessionId());
	}

	private FileUploadEntity getFileUploadEntity() {
		FileUploadEntity entity = new FileUploadEntity();
		entity.setId((long) 1);
		entity.setSessionId("sessionId");
		entity.setFilePath("/path/of/problem");
		return entity;
	}
}
