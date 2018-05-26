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
import pt.iscte.es2.dao.OptimizationConfigurationDao;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.State;
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.Arrays;

/**
 * Tests for the OptimizationConfiguration Datamanager when searching for an {@link OptimizationConfiguration}
 * by {@link OptimizationJobExecutions}
 */
public class SearchOptimizationConfigurationByOptimizationJobExecutionDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationConfigurationDao optimizationConfigurationDao;

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
	 * Should be a successful return of an {@link OptimizationConfiguration} when searching
	 * by an {@link OptimizationJobExecutions}
	 */
	@Test
	public void searchOptimizationConfigurationByExecutionIsValid() {
		Mockito.when(optimizationConfigurationDao.findByOptimizationJobExecutions(Mockito.any()))
			.thenReturn(getOptimizationConfigurationEntity());
		OptimizationConfiguration optimizationConfiguration = optimizationDataManager
			.searchOptimizationConfigurationByOptimizationJobExecution(getOptimizationJobExecutions());
		Assert.assertNotNull(optimizationConfiguration);
		Assert.assertEquals(new Integer(1), optimizationConfiguration.getId());
		Assert.assertEquals(1, optimizationConfiguration.getExecutions().size());
		Assert.assertEquals(new Integer(1), optimizationConfiguration.getExecutions().get(0).getId());
		Assert.assertEquals(State.Ready, optimizationConfiguration.getExecutions().get(0).getState());
	}

	private OptimizationConfigurationEntity getOptimizationConfigurationEntity() {
		OptimizationConfigurationEntity entity = new OptimizationConfigurationEntity();
		entity.setId((long) 1);
		entity.setOptimizationJobExecutions(Arrays.asList(getOptimizationJobExecutionsEntity()));
		return entity;
	}

	private OptimizationJobExecutionsEntity getOptimizationJobExecutionsEntity() {
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setId((long) 1);
		entity.setState(State.Ready);
		return entity;
	}

	private OptimizationJobExecutions getOptimizationJobExecutions() {
		OptimizationJobExecutions executions = new OptimizationJobExecutions();
		executions.setId(1);
		executions.setState(State.Ready);
		return executions;
	}

}
