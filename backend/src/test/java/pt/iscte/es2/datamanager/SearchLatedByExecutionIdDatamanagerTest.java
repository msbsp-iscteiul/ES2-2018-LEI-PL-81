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
import pt.iscte.es2.BeanMapping;
import pt.iscte.es2.dao.OptimizationJobExecutionsDao;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.Optional;

/**
 * Tests for the OptimizationConfiguration Datamanager searching for a Latex File
 */
public class SearchLatedByExecutionIdDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationJobExecutionsDao optimizationJobExecutionsDao;

	private DozerBeanMapper mapper;

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
		entity.setLatexPath("/path/latex/file");
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(entity));
		String path = optimizationDataManager.searchLatexPathByExecutionId(1);
		Assert.assertNotNull(path);
		Assert.assertEquals("/path/latex/file", path);
	}

	/**
	 * Should be NULL when no Path is found for a given {@link OptimizationJobExecutions} id.
	 */
	@Test
	public void unsuccessfulPath() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.empty());
		String path = optimizationDataManager.searchLatexPathByExecutionId(1);
		Assert.assertNull(path);
	}
}
