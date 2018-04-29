package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.OptimizationBusiness;
import pt.iscte.es2.dto.service.optimization.FileUploadRequest;
import pt.iscte.es2.dto.service.optimization.FileUploadResult;

/**
 * Tests for the OptimizationConfiguration Service when a file is uploaded
 */
public class FileUploadOptimizationConfigurationServiceTest {

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
	public void fileUploadServiceCallTest() {
		Mockito.when(optimizationBusiness.fileUpload(Mockito.any(), Mockito.any())).thenReturn(new FileUploadResult());
		Assert.assertNotNull(optimizationService.fileUpload(new FileUploadRequest()));
	}

}
