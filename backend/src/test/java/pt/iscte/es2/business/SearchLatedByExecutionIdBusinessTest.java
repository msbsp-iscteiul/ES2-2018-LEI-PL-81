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

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests for the OptimizationConfiguration Business searching for a Latex File
 */
public class SearchLatedByExecutionIdBusinessTest {

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
		Path path = Paths.get("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
		Mockito.when(optimizationDataManager.searchLatexPathByExecutionId(Mockito.any()))
			.thenReturn(path.toString());
		FileSystemResource resource = optimizationBusiness.searchLatexByExecutionId(1);
		Assert.assertEquals(resource.getFilename(), "containee-1.0-SNAPSHOT.jar");
	}

	/**
	 * Should be null when no file found.
	 */
	@Test
	public void noFileReturned() {
		Mockito.when(optimizationDataManager.searchLatexPathByExecutionId(Mockito.any()))
			.thenReturn(null);
		Assert.assertNull(optimizationBusiness.searchLatexByExecutionId(1));
	}

}
