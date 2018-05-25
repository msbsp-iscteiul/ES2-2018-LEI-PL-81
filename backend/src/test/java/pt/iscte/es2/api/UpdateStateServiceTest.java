package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.State;

/**
 * Tests for the OptimizationConfiguration Service when updating a State of an execution
 */
public class UpdateStateServiceTest {

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
		optimizationBusiness.updateState(Mockito.anyInt(), Mockito.any());
		optimizationService.updateState(1, State.Finished);
	}
}
