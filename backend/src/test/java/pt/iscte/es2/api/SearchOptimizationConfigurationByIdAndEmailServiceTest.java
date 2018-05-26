package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.OptimizationConfigurationResult;
import pt.iscte.es2.dto.service.optimization.SearchOptimizationConfigurationByIdAndEmailRequest;

/**
 * Tests for the OptimizationConfiguration Service when requested to search by id and email is made
 */
public class SearchOptimizationConfigurationByIdAndEmailServiceTest {

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
		Mockito.when(optimizationBusiness.searchOptimizationConfigurationByIdAndEmail(Mockito.anyInt(), Mockito.any()))
			.thenReturn(new OptimizationConfigurationResult());
		Assert.assertNotNull(optimizationService.searchOptimizationConfigurationByIdAndEmail(
			new SearchOptimizationConfigurationByIdAndEmailRequest()));
	}

}
