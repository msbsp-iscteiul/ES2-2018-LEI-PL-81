package pt.iscte.es2.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import pt.iscte.es2.algorithm_finder.AlgorithmFinder;
import pt.iscte.es2.client_jar_loader.LoadClientJarProblem;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.FileUpload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests for the OptimizationConfiguration Business for when uploading a Jar File
 */
public class FileUploadBusinessTest {

	@Mock
	private OptimizationDataManager optimizationDataManager;

	@InjectMocks
	private OptimizationBusiness optimizationBusiness;

	@Mock
	private LoadClientJarProblem loadClientJarProblem;

	@Before
	public void setup() {
		optimizationBusiness = new OptimizationBusinessImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test1() {
//		String sessionId = "someSessionId";
//		Path path = Paths.get("src/test/resources/data/containee-1.0-SNAPSHOT.jar");
//		String name = "containee-1.0-SNAPSHOT.jar";
//		String originalFileName = "containee-1.0-SNAPSHOT.jar";
//		String contentType = "text/plain";
//		byte[] content = null;
//		try {
//			content = Files.readAllBytes(path);
//		} catch (final IOException e) {
//			e.printStackTrace();
//		}
//		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
//		Mockito.when(optimizationDataManager.saveFileUpload(Mockito.any(), Mockito.any()))
//			.thenReturn(new FileUpload());
//		Problem<Solution<?>> problem = new Problem<Solution<?>>() {
//			@Override
//			public int getNumberOfVariables() {
//				return 2;
//			}
//
//			@Override
//			public int getNumberOfObjectives() {
//				return 2;
//			}
//
//			@Override
//			public int getNumberOfConstraints() {
//				return 2;
//			}
//
//			@Override
//			public String getName() {
//				return "Nimi";
//			}
//
//			@Override
//			public void evaluate(Solution<?> solution) {
//
//			}
//
//			@Override
//			public Solution<?> createSolution() {
//				return null;
//			}
//		};
//		try {
//			Mockito.when(new LoadClientJarProblem().loadProblemFromJar(Mockito.any())).thenReturn(problem);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		}

	}
}
