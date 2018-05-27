package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import pt.iscte.es2.business.OptimizationBusiness;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests for the OptimizationConfiguration Service when requesting an R File by execution id
 */
public class SearchRByExecutionIdServiceTest {

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
		Path path = Paths.get("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
		Mockito.when(optimizationBusiness.searchRByExecutionId(Mockito.anyInt()))
			.thenReturn(new FileSystemResource(path.toFile()));
		Assert.assertNotNull(optimizationService.searchRByExecutionId(1));
	}
}
