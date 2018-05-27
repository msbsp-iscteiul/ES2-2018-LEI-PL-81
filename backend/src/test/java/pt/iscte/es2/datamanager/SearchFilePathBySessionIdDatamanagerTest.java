package pt.iscte.es2.datamanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.dao.FileUploadDao;
import pt.iscte.es2.jpa.FileUploadEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for the OptimizationConfiguration Datamanager when searching a FilePath By SessionID
 */
public class SearchFilePathBySessionIdDatamanagerTest {

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
	 * Should return an existing Path of a given sessionId
	 */
	@Test
	public void shouldBeValidWhenSearchingAPathBySessionId() {
		FileUploadEntity entity1 = getFileUploadEntity((long) 1);
		FileUploadEntity entity2 = getFileUploadEntity((long) 2);
		List<FileUploadEntity> entities = Arrays.asList(entity1, entity2);
		Mockito.when(fileUploadDao.findBySessionId(Mockito.any())).thenReturn(entities);
		String path = optimizationDataManager.searchFilePathBySessionId("sessionId");
		Assert.assertNotNull(path);
		Assert.assertEquals("/path/to/problem", path);
	}

	public FileUploadEntity getFileUploadEntity(Long id) {
		FileUploadEntity entity = new FileUploadEntity();
		entity.setId(id);
		entity.setFilePath("/path/to/problem");
		entity.setSessionId("sessionId");
		return entity;
	}
}
