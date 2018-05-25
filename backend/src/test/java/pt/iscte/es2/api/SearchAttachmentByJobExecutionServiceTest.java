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
import pt.iscte.es2.dto.service.optimization.OptimizationConfigurationAttachmentRequest;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationJobSolutionRequest;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationJobSolutionResult;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests for the OptimizationConfiguration Service when searching for a Problem submitted earlier
 */
public class SearchAttachmentByJobExecutionServiceTest {

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
		Mockito.when(optimizationBusiness.searchAttachmentByJobExecution(Mockito.any()))
			.thenReturn(new FileSystemResource(path.toFile()));
		Assert.assertNotNull(optimizationService.searchAttachmentByJobExecution(
			new OptimizationConfigurationAttachmentRequest()));
	}
}
