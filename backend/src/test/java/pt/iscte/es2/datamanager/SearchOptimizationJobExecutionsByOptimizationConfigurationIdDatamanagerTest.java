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
import java.util.List;
import java.util.Optional;

/**
 * Tests for the OptimizationConfiguration Datamanager when searching for an {@link OptimizationJobExecutions}
 * by {@link OptimizationConfiguration} id
 */
public class SearchOptimizationJobExecutionsByOptimizationConfigurationIdDatamanagerTest {

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
	 * Should return a valid list of Executions searched by OptimizationConfiguration by id
	 */
	@Test
	public void validReturnExecutionsByOptimizationConfigurationID() {
		Mockito.when(optimizationConfigurationDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationConfigurationEntity()));
		List<OptimizationJobExecutions> executions = optimizationDataManager
			.searchOptimizationJobExecutionsByOptimizationConfigurationId(1);
		Assert.assertNotNull(executions);
		Assert.assertEquals(1, executions.size());
		Assert.assertEquals(new Integer(1), executions.get(0).getId());
		Assert.assertEquals(State.Ready, executions.get(0).getState());
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
}
