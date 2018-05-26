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
import pt.iscte.es2.dto.*;
import pt.iscte.es2.dto.service.optimization.SaveOptimizationConfigurationResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for the OptimizationConfiguration Business when saving and OptimizationConfiguration
 */
public class SaveOptimizationConfigurationBusinessTest {

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
	 * Must be Valid and with Success saving
	 */
	@Test
	public void successSaveOptimizationConfigurationWithoutCSVFile() {
		String path = "src/test/resources/data/containee-1.0-SNAPSHOT-integer_2.jar";
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(path);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()), Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, null);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(new Integer(1), result.getId());
	}

	/**
	 * Must be Invalid Save OptimizationConfiguration
	 */
	@Test
	public void invalidSaveOptimizationConfigurationWithoutCSVFile() {
		String path = "src/test/resources/data/containee-1.0-SNAPSHOT-integer_2.jar";
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any()))
			.thenReturn(path);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		savedOptimizationConfiguration.setId(null);
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()),
			Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, null);
		Assert.assertNotNull(result);
		Assert.assertNull(result.getId());
		Assert.assertEquals("Failed to submit the Optimization Configuration, please try again later.", result.getMessage());
	}

	/**
	 * Should return a failure error message if the CSV file is empty
	 */
	@Test
	public void saveOptimizationConfigurationWithEmptyCSVFile() {
		String strPath = "src/test/resources/data/empty.csv";
		Path path = Paths.get("src/test/resources/data/empty.csv");
		String name = "empty.csv";
		String originalFileName = "empty.csv";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(strPath);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()), Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, file);
		Assert.assertNotNull(result);
		Assert.assertEquals("The File is Empty!", result.getMessage());
	}

	/**
	 * Should return a failure error message if the file isn't .csv extension
	 */
	@Test
	public void saveOptimizationConfigurationWithNoCSVExtension() {
		String strPath = "src/test/resources/data/noCSVExtentionButWithContent.txt";
		Path path = Paths.get("src/test/resources/data/noCSVExtentionButWithContent.txt");
		String name = "noCSVExtentionButWithContent.txt";
		String originalFileName = "noCSVExtentionButWithContent.txt";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(strPath);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()), Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, file);
		Assert.assertNotNull(result);
		Assert.assertEquals("Incorrect Extension", result.getMessage());
	}

	/**
	 *
	 */
	@Test
	public void saveOptimizationConfigurationWithCSVExtensionValidObjectives() {
		String strPath = "src/test/resources/data/notEmpty_SameObjectives.csv";
		Path path = Paths.get("src/test/resources/data/notEmpty_SameObjectives.csv");
		String name = "notEmpty_SameObjectives.csv";
		String originalFileName = "notEmpty_SameObjectives.csv";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(strPath);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()), Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, file);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Integer(1), result.getId());
	}

	/**
	 *
	 */
	@Test
	public void saveOptimizationConfigurationWithCSVExtensionInvalidObjectives() {
		String strPath = "src/test/resources/data/notEmpty_DistinctObjectives.csv";
		Path path = Paths.get("src/test/resources/data/notEmpty_DistinctObjectives.csv");
		String name = "notEmpty_DistinctObjectives.csv";
		String originalFileName = "notEmpty_DistinctObjectives.csv";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(strPath);
		OptimizationConfiguration savedOptimizationConfiguration = getOptimizationConfiguration();
		Mockito.when(optimizationDataManager.saveOptimization(Mockito.any()))
			.thenReturn(savedOptimizationConfiguration);
		SaveOptimizationConfigurationResult result = optimizationBusiness.saveOptimization(
			"ProblemName", "Some Description", "email@email.com", "sessionId",
			Collections.singletonList(getOptimizationConfigurationVariables()), Collections.singletonList(getOptimizationConfigurationObjectives()),
			Collections.singletonList(getOptimizationConfigurationAlgorithms()), Collections.emptyList(),
			AlgorithmChoiceMethod.Automatic, 20, file);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Integer(1), result.getId());
	}

	private OptimizationConfiguration getOptimizationConfiguration() {
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setId(1);
		optimizationConfiguration.setProblemName("ProblemName");
		optimizationConfiguration.setDescription("Some Description");
		optimizationConfiguration.setEmail("email@email.com");
		optimizationConfiguration.setFilePath("src/test/resources/data/containee-1.0-SNAPSHOT-integer_2.jar");
		optimizationConfiguration.setExecutionMaxWaitTime(20);
		optimizationConfiguration.setVariables(
			Collections.singletonList(getOptimizationConfigurationVariables()));
		optimizationConfiguration.setObjectives(
			Collections.singletonList(getOptimizationConfigurationObjectives()));
		optimizationConfiguration.setAlgorithmChoiceMethod(AlgorithmChoiceMethod.Automatic);
		return optimizationConfiguration;
	}

	private OptimizationConfigurationVariables getOptimizationConfigurationVariables() {
		OptimizationConfigurationVariables optimizationConfigurationVariables = new OptimizationConfigurationVariables();
		optimizationConfigurationVariables.setName("Variable 1");
		return optimizationConfigurationVariables;
	}

	private OptimizationConfigurationObjectives getOptimizationConfigurationObjectives() {
		OptimizationConfigurationObjectives optimizationConfigurationObjectives = new OptimizationConfigurationObjectives();
		optimizationConfigurationObjectives.setName("Objective 1");
		return optimizationConfigurationObjectives;
	}

	private OptimizationConfigurationAlgorithms getOptimizationConfigurationAlgorithms() {
		OptimizationConfigurationAlgorithms algorithm = new OptimizationConfigurationAlgorithms();
		algorithm.setName("Algorithm 1");
		return algorithm;
	}
}
