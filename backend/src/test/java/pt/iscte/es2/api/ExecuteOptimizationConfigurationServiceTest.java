package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.ExecuteOptimizationConfigurationRequest;
import pt.iscte.es2.dto.service.optimization.ExecuteOptimizationConfigurationResult;

/**
 * Tests for the OptimizationConfiguration Service when its made an order to Execute an OptimizationConfiguration.
 */
public class ExecuteOptimizationConfigurationServiceTest {

	@Mock
	private OptimizationBusiness optimizationBusiness;

	@InjectMocks
	private OptimizationService optimizationService;

	@Before
	public void setup() {
		optimizationService = new OptimizationServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void successCall() {
		Mockito.when(optimizationBusiness.executeOptimizationConfiguration(Mockito.anyInt(), Mockito.any()))
			.thenReturn(new ExecuteOptimizationConfigurationResult());
		Assert.assertNotNull(optimizationService.executeOptimizationConfiguration(
			new ExecuteOptimizationConfigurationRequest()));
	}

}
