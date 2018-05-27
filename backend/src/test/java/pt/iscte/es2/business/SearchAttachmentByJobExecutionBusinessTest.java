package pt.iscte.es2.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.OptimizationJobExecutions;

/**
 * Tests for the OptimizationConfiguration Business searching for a Jar File (problem submitted by the user)
 */
public class SearchAttachmentByJobExecutionBusinessTest {

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
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setFilePath("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
		Mockito.when(optimizationDataManager.searchOptimizationJobExecutionsById(Mockito.any()))
			.thenReturn(new OptimizationJobExecutions());
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByOptimizationJobExecution(Mockito.any()))
			.thenReturn(optimizationConfiguration);
		FileSystemResource resource = optimizationBusiness.searchAttachmentByJobExecution(1);
		Assert.assertEquals(resource.getFilename(), "containee-1.0-SNAPSHOT.jar");
	}

	/**
	 * Should be INVALID (Null) when no file is found
	 */
	@Test
	public void fileReturnedWithInvalid() {
		Mockito.when(optimizationDataManager.searchOptimizationJobExecutionsById(Mockito.any()))
			.thenReturn(new OptimizationJobExecutions());
		Mockito.when(optimizationDataManager.searchOptimizationConfigurationByOptimizationJobExecution(Mockito.any()))
			.thenReturn(null);
		FileSystemResource resource = optimizationBusiness.searchAttachmentByJobExecution(1);
		Assert.assertNull(resource);
	}
}
