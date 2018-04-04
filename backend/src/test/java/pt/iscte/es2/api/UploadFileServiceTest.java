package pt.iscte.es2.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.business.UploadBusiness;
import pt.iscte.es2.dto.serviceview.upload.UploadRequest;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

/**
 * Tests for the UploadFile Service
 */
public class UploadFileServiceTest {

	@Mock
	private UploadBusiness uploadBusiness;

	@InjectMocks
	private UploadService uploadService;

	@Before
	public void setup() {
		uploadService = new UploadServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Checks the response when calling UploadFile Service
	 */
	@Test
	public void uploadFileResponse() {
		Mockito.when(uploadBusiness.uploadFile(Mockito.any(), Mockito.any()))
			.thenReturn(new UploadResult());
		Assert.assertNotNull(uploadService.uploadFile(new UploadRequest()));
	}

}
