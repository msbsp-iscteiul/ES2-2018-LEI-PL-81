package pt.iscte.es2.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.datamanager.UploadDatamanager;
import pt.iscte.es2.dto.serviceview.upload.UploadResult;

import java.io.File;

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

	// TODO
	@Test
	public void uploadFileWithEmptyFile() {
		// MultipartFile file = getMultipartFile();
	}

	// TODO
	@Test
	public void uploadFileWithSuccess() {
		UploadResult result = new UploadResult(1,2,2);
	}

//	private MultipartFile getMultipartFile() {
//		MultipartFile file = (MultipartFile) new File("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
//		return file;
//	}
}
