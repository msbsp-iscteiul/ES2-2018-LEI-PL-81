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
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;

import java.util.Collections;

/**
 * Tests for the OptimizationConfiguration Datamanager when saving an {@link OptimizationConfiguration}
 */
public class SaveOptimizationDatamanagerTest {

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
	 * Must be Successful when saving an {@link OptimizationConfiguration} which will then return
	 * the saved {@link OptimizationConfiguration} with an id
	 */
	@Test
	public void successfulSaveOptimizationConfiguration() {
		Mockito.when(optimizationConfigurationDao.save(Mockito.any()))
			.thenReturn(getOptimizationConfigurationEntity());
		OptimizationConfiguration savedOptimizationConfiguration = optimizationDataManager
			.saveOptimization(getOptimizationConfiguration());
		Assert.assertNotNull(savedOptimizationConfiguration);
		Assert.assertEquals(new Integer(1), savedOptimizationConfiguration.getId());
		Assert.assertEquals("email@email.com", savedOptimizationConfiguration.getEmail());
		Assert.assertEquals("Problem 1", savedOptimizationConfiguration.getProblemName());
		Assert.assertEquals("/path/to/problem", savedOptimizationConfiguration.getFilePath());
	}

	private OptimizationConfigurationEntity getOptimizationConfigurationEntity() {
		OptimizationConfigurationEntity entity = new OptimizationConfigurationEntity();
		entity.setId((long) 1);
		entity.setProblemName("Problem 1");
		entity.setEmail("email@email.com");
		entity.setFilePath("/path/to/problem");
		return entity;
	}

	private OptimizationConfiguration getOptimizationConfiguration() {
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setEmail("email@email.com");
		optimizationConfiguration.setProblemName("Problem 1");
		optimizationConfiguration.setDescription("Some Description");
		return optimizationConfiguration;
	}
}
