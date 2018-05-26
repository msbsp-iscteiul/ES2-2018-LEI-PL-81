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
import java.util.Collections;

/**
 * Tests for the OptimizationConfiguration Datamanager when saving and {@link OptimizationJobExecutions}
 */
public class SaveExecutionOptimizationConfigurationDatamanagerTest {

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
	 * Should be a valid saved object return the ID of the persistent Execution ready to execute
	 */
	@Test
	public void successfulPersistExecution() {
		Mockito.when(optimizationConfigurationDao.save(Mockito.any())).thenReturn(getOptimizationConfigurationEntity());
		Integer id = optimizationDataManager.saveExecutionOptimizationConfiguration(getOptimizationConfiguration());
		Assert.assertNotNull(id);
		Assert.assertEquals(new Integer(1), id);
	}

	private OptimizationConfiguration getOptimizationConfiguration() {
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setId(1);
		optimizationConfiguration.setExecutions(Arrays.asList(getOptimizationJobExecutions()));
		return optimizationConfiguration;
	}

	private OptimizationJobExecutions getOptimizationJobExecutions() {
		OptimizationJobExecutions execution = new OptimizationJobExecutions();
		execution.setId(1);
		execution.setState(State.Ready);
		execution.setSolutions(Collections.emptyList());
		return execution;
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
		entity.getOptimizationJobSolutions().addAll(Collections.emptyList());
		return entity;
	}
}
