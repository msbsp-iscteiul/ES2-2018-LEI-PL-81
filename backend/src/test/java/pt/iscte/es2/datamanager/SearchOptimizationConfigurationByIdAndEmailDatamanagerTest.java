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

/**
 * Tests for the OptimizationConfiguration Datamanager when search for an {@link OptimizationConfiguration} by id and email
 */
public class SearchOptimizationConfigurationByIdAndEmailDatamanagerTest {

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
	 * Should be successful when an {@link OptimizationConfiguration} exists when searched by email and id
	 */
	@Test
	public void successWhenOptimizationConfigurationExists() {
		Mockito.when(optimizationConfigurationDao.findByIdAndEmail(Mockito.any(), Mockito.any()))
			.thenReturn(getOptimizationConfigurationEntity());
		OptimizationConfiguration result = optimizationDataManager
			.searchOptimizationConfigurationByIdAndEmail(1, "email@email.com");
		Assert.assertNotNull(result);
		Assert.assertEquals(new Integer(1), result.getId());
		Assert.assertEquals("email@email.com", result.getEmail());
	}

	private OptimizationConfigurationEntity getOptimizationConfigurationEntity() {
		OptimizationConfigurationEntity entity = new OptimizationConfigurationEntity();
		entity.setId((long) 1);
		entity.setEmail("email@email.com");
		return entity;
	}

}
