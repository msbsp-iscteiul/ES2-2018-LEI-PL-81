package pt.iscte.es2.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.FileUpload;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadOptimizationConfigurationBusinessTest {

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
	public void uploadFileWithSuccess() {
		String sessionId = "someSessionId";
		Path path = Paths.get("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
		String name = "containee-1.0-SNAPSHOT.jar";
		String originalFileName = "containee-1.0-SNAPSHOT.jar";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.saveFileUpload(Mockito.any(), Mockito.any())).thenReturn(getFileUpload(sessionId));
		Assert.assertNotNull(optimizationBusiness.fileUpload(sessionId, result));
	}

	/**
	 * Should return null since the file passed is empty
	 */
	@Test
	public void uploadFileWithFileEmpty() {
		String sessionId = "someSessionId";
		Path path = Paths.get("src/test/resources/data/empty.txt");
		String name = "empty.txt";
		String originalFileName = "empty.txt";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.saveFileUpload(Mockito.any(), Mockito.any())).thenReturn(getFileUpload(sessionId));
		Assert.assertNull(optimizationBusiness.fileUpload(sessionId, result));
	}

	private FileUpload getFileUpload(String sessionId) {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setId(1);
		fileUpload.setFilePath("backend/target/jars/containee-1.0-SNAPSHOT.jar");
		fileUpload.setSessionId(sessionId);
		return fileUpload;
	}

}
