package pt.iscte.es2.datamanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.dao.OptimizationConfigurationDao;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.SummaryOptimizationConfiguration;
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for the OptimizationConfiguration Datamanager when search for an {@link OptimizationConfiguration} by email
 */
public class SearchOptimizationConfigurationByEmailDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationConfigurationDao optimizationConfigurationDao;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Must be VALID ans Success delivering the {@link SummaryOptimizationConfiguration}
	 * of the {@link OptimizationConfiguration} associated by email
	 */
	@Test
	public void successSummariesCreation() {
		OptimizationConfigurationEntity entity1 = getOptimizationConfigurationEntity((long) 1, "Problem 1");
		OptimizationConfigurationEntity entity2 = getOptimizationConfigurationEntity((long) 2, "Problem 2");
		List<OptimizationConfigurationEntity> list = Arrays.asList(entity1, entity2);
		Mockito.when(optimizationConfigurationDao.findByEmail(Mockito.any())).thenReturn(list);
		List<SummaryOptimizationConfiguration> summaries = optimizationDataManager
			.searchOptimizationConfigurationByEmail("email@email.comt");
		Assert.assertNotNull(summaries);
		Assert.assertEquals(2, summaries.size());
		Assert.assertEquals(new Integer(1), summaries.get(0).getId());
		Assert.assertEquals(new Integer(2), summaries.get(1).getId());
		Assert.assertEquals("Problem 1", summaries.get(0).getProblemName());
		Assert.assertEquals("Problem 2", summaries.get(1).getProblemName());
	}

	private OptimizationConfigurationEntity getOptimizationConfigurationEntity(Long id, String problemName) {
		OptimizationConfigurationEntity entity = new OptimizationConfigurationEntity();
		entity.setId(id);
		entity.setEmail("email@email.com");
		entity.setProblemName(problemName);
		return entity;
	}
}
