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
import pt.iscte.es2.dto.State;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.Date;
import java.util.Optional;

public class UpdateStateDatamanagerTest {

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
	 * Should be VALID when updating a State of an Existing OptimizationJobExecution to Running State.
	 */
	@Test
	public void validWhenUpdatingToRunningState() {
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setState(State.Running);
		Mockito.when(optimizationJobExecutionsDao.getOne(Mockito.any()))
			.thenReturn(entity);
		Mockito.when(optimizationJobExecutionsDao.save(Mockito.any()))
			.thenReturn(entity);
		OptimizationJobExecutions execution = optimizationDataManager.updateState(1, State.Running);
		Assert.assertNotNull(execution);
		Assert.assertEquals(State.Running, execution.getState());
	}

	/**
	 * Should be VALID when updating a State of an Existing OptimizationJobExecution to Finished State.
	 */
	@Test
	public void validWhenUpdatingToFinishedState() {
		Date endDate = new Date();
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity();
		entity.setState(State.Finished);
		entity.setEndDate(endDate);
		Mockito.when(optimizationJobExecutionsDao.getOne(Mockito.any()))
			.thenReturn(entity);
		Mockito.when(optimizationJobExecutionsDao.save(Mockito.any()))
			.thenReturn(entity);
		OptimizationJobExecutions execution = optimizationDataManager.updateState(1, State.Finished);
		Assert.assertNotNull(execution);
		Assert.assertEquals(State.Finished, execution.getState());
		Assert.assertEquals(endDate.toString(), execution.getEndDate().toString());
	}

}
