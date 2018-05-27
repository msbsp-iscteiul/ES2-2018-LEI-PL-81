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
import pt.iscte.es2.dto.State;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.Optional;

/**
 * Tests for the OptimizationConfiguration Datamanager when searching for an {@link OptimizationJobExecutions}
 */
public class SearchOptimizationJobExecutionsByIdDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationJobExecutionsDao optimizationJobExecutionsDao;

	private DozerBeanMapper mapper;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		MockitoAnnotations.initMocks(this);
		mapper = new DozerBeanMapper();
		mapper.addMapping(new BeanMapping());
		ReflectionTestUtils.setField(optimizationDataManager, "mapper", mapper);
	}

	/**
	 * Should be a valid return of an {@link OptimizationJobExecutions} when searched by Id
	 */
	@Test
	public void validSearchOfOptimizationJobExecutionsById() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationJobExecutionsEntity()));
		OptimizationJobExecutions execution = optimizationDataManager.searchOptimizationJobExecutionsById(1);
		Assert.assertNotNull(execution);
		Assert.assertEquals(new Integer(1), execution.getId());
		Assert.assertEquals(State.Ready, execution.getState());
	}

	/**
	 * Should be a NULL return if no {@link OptimizationJobExecutions} is found by id
	 */
	@Test
	public void invalidSearchOfOptimizationJobExecutionsById() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.empty());
		OptimizationJobExecutions execution = optimizationDataManager.searchOptimizationJobExecutionsById(1);
		Assert.assertNull(execution);
	}

	private OptimizationJobExecutionsEntity getOptimizationJobExecutionsEntity() {
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setId((long) 1);
		entity.setState(State.Ready);
		return entity;
	}

}
