package pt.iscte.es2.datamanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.dao.OptimizationJobExecutionsDao;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.Optional;

/**
 * Tests for the OptimizationConfiguration Datamanager searching for a R File
 */
public class SearchRByExecutionIdDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationJobExecutionsDao optimizationJobExecutionsDao;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Success when retrieving a path from an existing {@link OptimizationJobExecutions}
	 */
	@Test
	public void successfulPath() {
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setrPath("/path/r/file");
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(entity));
		String path = optimizationDataManager.searchRPathByExecutionId(1);
		Assert.assertNotNull(path);
		Assert.assertEquals("/path/r/file", path);
	}

	/**
	 * Should be NULL when no Path is found for a given {@link OptimizationJobExecutions} id.
	 */
	@Test
	public void unsuccessfulPath() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.empty());
		String path = optimizationDataManager.searchRPathByExecutionId(1);
		Assert.assertNull(path);
	}

}
