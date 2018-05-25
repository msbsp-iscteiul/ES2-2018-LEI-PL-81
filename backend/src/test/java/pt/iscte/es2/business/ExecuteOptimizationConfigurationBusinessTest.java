package pt.iscte.es2.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import pt.iscte.es2.Sender;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.service.optimization.ExecuteOptimizationConfigurationResult;

/**
 * Tests for the OptimizationConfiguration Business for when ordering an Execution of OptimizationConfiguration
 */
public class ExecuteOptimizationConfigurationBusinessTest {

	@Mock
	private OptimizationDataManager optimizationDataManager;

	@InjectMocks
	private OptimizationBusiness optimizationBusiness;

	@Mock
	private Sender sender;

	@Before
	public void setup() {
		optimizationBusiness = new OptimizationBusinessImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Should be Successful when execution with correct ID associated with OptimizationCOnfiguration
	 */
	@Test
	public void executionWithSuccess() {
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByIdAndEmail(Mockito.any(), Mockito.any()))
			.thenReturn(new OptimizationConfiguration());
		Integer savingResultId = 1;
		Mockito.when(optimizationDataManager.saveExecutionOptimizationConfiguration(Mockito.any()))
			.thenReturn(savingResultId);
		ExecuteOptimizationConfigurationResult result = optimizationBusiness.executeOptimizationConfiguration(1, "mike@mike.pt");
		Assert.assertNotNull(result);
		Assert.assertEquals(new Integer(1), result.getId());
		Assert.assertEquals("SUCCESS", result.getMessage());
	}

	/**
	 * Should be Successful when execution with correct ID associated with OptimizationCOnfiguration
	 */
	@Test
	public void executionWithoutSuccess() {
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByIdAndEmail(Mockito.any(), Mockito.any()))
			.thenReturn(null);
		Mockito.when(optimizationDataManager.saveExecutionOptimizationConfiguration(Mockito.any()))
			.thenReturn(null);
		ExecuteOptimizationConfigurationResult result = optimizationBusiness.executeOptimizationConfiguration(1, "mike@mike.pt");
		Assert.assertNotNull(result);
		Assert.assertEquals(null, result.getId());
		Assert.assertEquals("FAILED", result.getMessage());
	}

}
