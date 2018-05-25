package pt.iscte.es2.datamanager;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import pt.iscte.es2.dao.FileUploadDao;
import pt.iscte.es2.dto.FileUpload;
import pt.iscte.es2.jpa.FileUploadEntity;

public class FileUploadOptimizationConfigurationDataManagerTest {

	private DozerBeanMapper mapper;

	@Mock
	private FileUploadDao fileUploadDao;

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		mapper = new DozerBeanMapper();
		ReflectionTestUtils.setField(optimizationDataManager, "mapper", mapper);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Ansioyhteenveto is saved.
	 */
	@Test
	public void saveAnsioyhteenvetoDataManagerTest() {
		FileUploadEntity entity = new FileUploadEntity();
		entity.setId(new Long(1));

		Mockito.when(fileUploadDao.exists(Mockito.any())).thenReturn(true);
		Mockito.when(fileUploadDao.saveAndFlush(Mockito.any(FileUploadEntity.class))).thenReturn(entity);

		FileUpload result = optimizationDataManager.saveFileUpload("someSessionId", "somePath");
		// Assert.assertEquals(1, result.getId());
	}


}
