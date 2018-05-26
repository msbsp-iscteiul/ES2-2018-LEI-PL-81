package pt.iscte.es2.datamanager;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import pt.iscte.es2.BeanMapping;
import pt.iscte.es2.dao.OptimizationJobExecutionsDao;
import pt.iscte.es2.dto.OptimizationJobExecutions;
import pt.iscte.es2.dto.OptimizationJobSolutions;
import pt.iscte.es2.dto.State;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;
import pt.iscte.es2.jpa.OptimizationJobSolutionsEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Tests for the OptimizationConfiguration Datamanager when persisting an {@link OptimizationJobSolutions}
 */
public class SaveOptimizationJobSolutionDatamanagerTest {

	@InjectMocks
	private OptimizationDataManager optimizationDataManager;

	@Mock
	private OptimizationJobExecutionsDao optimizationJobExecutionsDao;

	private DozerBeanMapper mapper;

	@Before
	public void setup() {
		optimizationDataManager = new OptimizationDataManagerImpl();
		MockitoAnnotations.initMocks(this);
		mapper = new DozerBeanMapper();
		mapper.addMapping(new BeanMapping());
		ReflectionTestUtils.setField(optimizationDataManager, "mapper", mapper);
	}

	/**
	 * Should return a List of {@link OptimizationJobSolutions} for a given {@link OptimizationJobExecutions}
	 * by id, without any latexPath or rPath.
	 */
	@Test
	public void successOptimizationJobSolutionsWithNoLatexPathAndNoRPath() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationJobExecutionsEntity((long) 1, State.Running, "", "", getOptimizationJobSolutionsEntity())));
		Mockito.when(optimizationJobExecutionsDao.saveAndFlush(Mockito.any()))
			.thenReturn(getOptimizationJobExecutionsEntity((long) 1, State.Finished, "", "", getOptimizationJobSolutionsEntity()));
		List<OptimizationJobSolutions> solutions = optimizationDataManager
			.saveOptimizationJobSolution(1, Arrays.asList(getOptimizationJobSolutions()), State.Finished, "", "");
		Assert.assertNotNull(solutions);
		Assert.assertEquals(1, solutions.size());
		Assert.assertEquals("Algorithm 1", solutions.get(0).getAlgorithmName());
		Assert.assertEquals("Best Quality", solutions.get(0).getQuality());
		Assert.assertEquals("Best Solution", solutions.get(0).getSolution());
		Assert.assertEquals("Best Solution Quality", solutions.get(0).getSolutionQuality());
	}

	/**
	 * Should return a List of {@link OptimizationJobSolutions} for a given {@link OptimizationJobExecutions}
	 * by id, with a Latex Path and no R Path.
	 */
	@Test
	public void successOptimizationJobSolutionsWithLatexPathAndNoRPath() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationJobExecutionsEntity((long) 1, State.Running, "/path/to/latex", "", getOptimizationJobSolutionsEntity())));
		Mockito.when(optimizationJobExecutionsDao.saveAndFlush(Mockito.any()))
			.thenReturn(getOptimizationJobExecutionsEntity((long) 1, State.Finished, "/path/to/latex", "", getOptimizationJobSolutionsEntity()));
		List<OptimizationJobSolutions> solutions = optimizationDataManager
			.saveOptimizationJobSolution(1, Arrays.asList(getOptimizationJobSolutions()), State.Finished, "/path/to/latex", "");
		Assert.assertNotNull(solutions);
		Assert.assertEquals(1, solutions.size());
		Assert.assertEquals("Algorithm 1", solutions.get(0).getAlgorithmName());
		Assert.assertEquals("Best Quality", solutions.get(0).getQuality());
		Assert.assertEquals("Best Solution", solutions.get(0).getSolution());
		Assert.assertEquals("Best Solution Quality", solutions.get(0).getSolutionQuality());
	}

	/**
	 * Should return a List of {@link OptimizationJobSolutions} for a given {@link OptimizationJobExecutions}
	 * by id, with no Latex Path but with R Path
	 */
	@Test
	public void successOptimizationJobSolutionsWithNoLatexPathButWithRPath() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationJobExecutionsEntity((long) 1, State.Running, "", "/path/to/r", getOptimizationJobSolutionsEntity())));
		Mockito.when(optimizationJobExecutionsDao.saveAndFlush(Mockito.any()))
			.thenReturn(getOptimizationJobExecutionsEntity((long) 1, State.Finished, "", "/path/to/r", getOptimizationJobSolutionsEntity()));
		List<OptimizationJobSolutions> solutions = optimizationDataManager
			.saveOptimizationJobSolution(1, Arrays.asList(getOptimizationJobSolutions()), State.Finished, "", "/path/to/r");
		Assert.assertNotNull(solutions);
		Assert.assertEquals(1, solutions.size());
		Assert.assertEquals("Algorithm 1", solutions.get(0).getAlgorithmName());
		Assert.assertEquals("Best Quality", solutions.get(0).getQuality());
		Assert.assertEquals("Best Solution", solutions.get(0).getSolution());
		Assert.assertEquals("Best Solution Quality", solutions.get(0).getSolutionQuality());
	}

	/**
	 * Should return a List of {@link OptimizationJobSolutions} for a given {@link OptimizationJobExecutions}
	 * by id, with both Latex Path and R Path
	 */
	@Test
	public void successOptimizationJobSolutionsWithBothLatexAndRPaths() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.of(getOptimizationJobExecutionsEntity((long) 1, State.Running, "/path/to/latex", "/path/to/r", getOptimizationJobSolutionsEntity())));
		Mockito.when(optimizationJobExecutionsDao.saveAndFlush(Mockito.any()))
			.thenReturn(getOptimizationJobExecutionsEntity((long) 1, State.Finished, "/path/to/latex", "/path/to/r", getOptimizationJobSolutionsEntity()));
		List<OptimizationJobSolutions> solutions = optimizationDataManager
			.saveOptimizationJobSolution(1, Arrays.asList(getOptimizationJobSolutions()), State.Finished, "/path/to/latex", "/path/to/r");
		Assert.assertNotNull(solutions);
		Assert.assertEquals(1, solutions.size());
		Assert.assertEquals("Algorithm 1", solutions.get(0).getAlgorithmName());
		Assert.assertEquals("Best Quality", solutions.get(0).getQuality());
		Assert.assertEquals("Best Solution", solutions.get(0).getSolution());
		Assert.assertEquals("Best Solution Quality", solutions.get(0).getSolutionQuality());
	}

	/**
	 * Should return a List of {@link OptimizationJobSolutions} for a given {@link OptimizationJobExecutions}
	 * by id, with both Latex Path and R Path
	 */
	@Test
	public void successOptimizationJobSolutionsWithNoOptimizationJobExecutionsFound() {
		Mockito.when(optimizationJobExecutionsDao.findById(Mockito.any()))
			.thenReturn(Optional.empty());
		List<OptimizationJobSolutions> solutions = optimizationDataManager
			.saveOptimizationJobSolution(1, Arrays.asList(getOptimizationJobSolutions()), State.Finished, "/path/to/latex", "/path/to/r");
		Assert.assertNotNull(solutions);
		Assert.assertTrue(solutions.isEmpty());
	}

	private OptimizationJobExecutionsEntity getOptimizationJobExecutionsEntity(
		Long id, State state, String latexPath, String rPath, OptimizationJobSolutionsEntity solution) {
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setId(id);
		entity.setState(state);
		entity.setLatexPath(latexPath);
		entity.setrPath(rPath);
		entity.getOptimizationJobSolutions().add(solution);
		return entity;
	}

	private OptimizationJobSolutions getOptimizationJobSolutions() {
		OptimizationJobSolutions solution = new OptimizationJobSolutions();
		solution.setAlgorithmName("Algorithm 1");
		solution.setQuality("Best Quality");
		solution.setSolution("Best Solution");
		solution.setSolutionQuality("Best Solution Quality");
		return solution;
	}

	private OptimizationJobSolutionsEntity getOptimizationJobSolutionsEntity() {
		OptimizationJobSolutionsEntity entity = new OptimizationJobSolutionsEntity();
		entity.setAlgorithmName("Algorithm 1");
		entity.setQuality("Best Quality");
		entity.setSolution("Best Solution");
		entity.setSolutionQuality("Best Solution Quality");
		return entity;
	}
}
