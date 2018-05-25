package pt.iscte.es2.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.SummaryOptimizationConfiguration;
import pt.iscte.es2.dto.service.optimization.OptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SummaryOptimizationConfigurationResult;

import java.util.Arrays;

/**
 * Tests for the OptimizationConfiguration Business for when searching for an OptimizationConfiguration by id and email
 */
public class SearchOptimizationConfigurationByIdAndEmailBusinessTest {

	@Mock
	private OptimizationDataManager optimizationDataManager;

	@InjectMocks
	private OptimizationBusiness optimizationBusiness;

	@Before
	public void setup() {
		optimizationBusiness = new OptimizationBusinessImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Should be valid when searching for an OptimizationConfiguration that exists on Database
	 */
	@Test
	public void validWhenOptimizationConfigurationExists() {
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setId(1);
		optimizationConfiguration.setEmail("mike@mike.pt");
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByIdAndEmail(Mockito.any(), Mockito.any()))
			.thenReturn(optimizationConfiguration);
		OptimizationConfigurationResult result = optimizationBusiness.searchOptimizationConfigurationByIdAndEmail(1, "mike@mike.pt");
		Assert.assertNotNull(result.getOptimizationConfiguration());
		Assert.assertEquals(new Integer(1), result.getOptimizationConfiguration().getId());
		Assert.assertEquals("mike@mike.pt", result.getOptimizationConfiguration().getEmail());
	}

	/**
	 * Should be valid when searching for an OptimizationConfiguration that exists on Database
	 */
	@Test
	public void invalidWhenNoOptimizationConfigurationExists() {
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByIdAndEmail(Mockito.any(), Mockito.any()))
			.thenReturn(null);
		OptimizationConfigurationResult result = optimizationBusiness.searchOptimizationConfigurationByIdAndEmail(1, "mike@mike.pt");
		Assert.assertNull(result.getOptimizationConfiguration());
	}

}
