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
import pt.iscte.es2.dto.service.optimization.ExecuteOptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SummaryOptimizationConfigurationResult;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for the OptimizationConfiguration Business for when searching for an OptimizationConfiguration by email
 */
public class SearchOptimizationConfigurationByEmailBusinessTest {

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
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByEmail(Mockito.any()))
			.thenReturn(Arrays.asList(new SummaryOptimizationConfiguration()));
		SummaryOptimizationConfigurationResult result = optimizationBusiness.searchOptimizationConfigurationByEmail("mike@mike.pt");
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.getSummary().size());
	}

	/**
	 * Should return a Result but with no {@link SummaryOptimizationConfiguration}
	 */
	@Test
	public void invalidWhenNoOptimizationConfigurationExists() {
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByEmail(Mockito.any()))
			.thenReturn(Collections.emptyList());
		SummaryOptimizationConfigurationResult result = optimizationBusiness.searchOptimizationConfigurationByEmail("mike@mike.pt");
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getSummary().isEmpty());
	}
}
