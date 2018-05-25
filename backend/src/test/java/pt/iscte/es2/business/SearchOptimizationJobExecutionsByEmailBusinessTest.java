package pt.iscte.es2.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.State;
import pt.iscte.es2.dto.SummaryOptimizationConfiguration;
import pt.iscte.es2.dto.service.optimization.OptimizationJobExecutionsResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * Tests for the OptimizationConfiguration Business searching for all OptimizationJobExecutions by user email.
 */
public class SearchOptimizationJobExecutionsByEmailBusinessTest {

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
	 * Should return a File with success.
	 */
	@Test
	public void fileReturnedWithSuccess() {
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByEmail(Mockito.any()))
			.thenReturn(Arrays.asList(getSummaryOptimizationConfiguration()));

		Mockito.when(optimizationDataManager.searchOptimizationJobExecutionsByOptimizationConfigurationId(Mockito.any()))
			.thenReturn(Arrays.asList(getOptimizationJobExecutions()));

		OptimizationJobExecutionsResult result = optimizationBusiness
			.searchOptimizationJobExecutionsByEmail("mike@mike.pt");

		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.getExecutions().size());
	}


	private SummaryOptimizationConfiguration getSummaryOptimizationConfiguration() {
		SummaryOptimizationConfiguration summaryOptimizationConfiguration = new SummaryOptimizationConfiguration();
		summaryOptimizationConfiguration.setId(1);
		summaryOptimizationConfiguration.setCreatedDate(new Date());
		summaryOptimizationConfiguration.setProblemName("Problem 1");
		summaryOptimizationConfiguration.setDescription("Description 1");
		return summaryOptimizationConfiguration;
	}

	private OptimizationJobExecutions getOptimizationJobExecutions() {
		OptimizationJobExecutions optimizationJobExecutions = new OptimizationJobExecutions();
		optimizationJobExecutions.setId(1);
		optimizationJobExecutions.setState(State.Ready);
		optimizationJobExecutions.setStartDate(new Date());
		optimizationJobExecutions.setEndDate(null);
		optimizationJobExecutions.setSolutions(Collections.emptyList());
		return optimizationJobExecutions;
	}
}
