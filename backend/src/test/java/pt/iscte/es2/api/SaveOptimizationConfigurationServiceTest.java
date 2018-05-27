package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationConfigurationRequest;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationConfigurationResult;

/**
 * Tests for the OptimizationConfiguration Service when requested to save an Optimization
 */
public class SaveOptimizationConfigurationServiceTest {

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
		Mockito.when(optimizationBusiness.saveOptimization(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
			Mockito.any(), Mockito.anyList(), Mockito.anyList(), Mockito.anyList(), Mockito.any(),
			Mockito.any(), Mockito.any()))
			.thenReturn(new SaveOptimizationConfigurationResult());
		Assert.assertNotNull(optimizationService.saveOptimization(new SaveOptimizationConfigurationRequest()));
	}
}
