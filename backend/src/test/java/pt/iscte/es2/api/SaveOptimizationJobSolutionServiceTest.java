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
import pt.iscte.es2.dto.service.optimization.SaveOptimizationJobSolutionRequest;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationJobSolutionResult;

/**
 * Tests for the OptimizationConfiguration Service when requested to search by id and email is made
 */
public class SaveOptimizationJobSolutionServiceTest {

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
		Mockito.when(optimizationBusiness.saveOptimizationJobSolution(Mockito.anyInt(), Mockito.any(), Mockito.anyList(), Mockito.any(), Mockito.any()))
			.thenReturn(new SaveOptimizationJobSolutionResult("Success"));
		Assert.assertNotNull(optimizationService.saveOptimizationJobSolution(new SaveOptimizationJobSolutionRequest()));
	}
}

