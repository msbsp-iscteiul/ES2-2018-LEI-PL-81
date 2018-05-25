package pt.iscte.es2.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.State;

/**
 * Tests for the OptimizationConfiguration Business when updating a State of an execution
 */
public class UpdateStateBusinessTest {

	@Mock
	private OptimizationDataManager optimizationDataManager;

	@InjectMocks
	private OptimizationBusiness optimizationBusiness;

	@Before
	public void setup() {
		optimizationBusiness = new OptimizationBusinessImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void updateTheState() {
		Mockito.when(optimizationDataManager.updateState(Mockito.any(), Mockito.any()))
			.thenReturn(new OptimizationJobExecutions());
		optimizationBusiness.updateState(1, State.Ready);
	}

}
