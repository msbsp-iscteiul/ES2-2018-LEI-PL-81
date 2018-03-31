package pt.iscte.es2.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.datamanager.UploadDatamanager;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

/**
 * Tests for the UploadFile Business
 */
public class UploadFileBusinessTest {

	@Mock
	private UploadDatamanager uploadDatamanager;

	@InjectMocks
	private UploadBusiness uploadBusiness;

	@Before
	public void setup() {
		uploadBusiness = new UploadBusinessImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void uploadFileWithSuccess() {
		UploadResult result = new UploadResult(2,2,2);
	}

}
