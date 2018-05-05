package pt.iscte.es2.business;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pt.iscte.es2.datamanager.OptimizationDataManager;
import pt.iscte.es2.dto.AlgorithmChoiceMethod;
import pt.iscte.es2.dto.OptimizationConfiguration;
import pt.iscte.es2.dto.OptimizationConfigurationObjectives;
import pt.iscte.es2.dto.OptimizationConfigurationVariables;

import java.util.Arrays;
import java.util.Collections;

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


	// TODO
	@Test
	public void test1() {
		String problemName = "problemName";
		String email = "email@email.com";
		Integer maxExecutionTime = 20;
		String sessionId = "someSessionId";
		String filePath = "/file/path";
		Mockito.when(optimizationDataManager.searchFilePathBySessionId(Mockito.any())).thenReturn(filePath);
		OptimizationConfiguration optimizationConfiguration = getOptimizationConfiguration();
		optimizationDataManager.saveOptimization(optimizationConfiguration);
		// optimizationBusiness.saveOptimization(problemName, email, );
	}


	private OptimizationConfiguration getOptimizationConfiguration() {
		OptimizationConfiguration optimizationConfiguration = new OptimizationConfiguration();
		optimizationConfiguration.setProblemName("ProblemName");
		optimizationConfiguration.setEmail("email@email.com");
		optimizationConfiguration.setFilePath("/file/path");
		optimizationConfiguration.setExecutionMaxWaitTime(20);
		optimizationConfiguration.setVariables(Collections.singletonList(getOptimizationConfigurationVariables()));
		optimizationConfiguration.setObjectives(Collections.singletonList(getOptimizationConfigurationObjectives()));
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
}
