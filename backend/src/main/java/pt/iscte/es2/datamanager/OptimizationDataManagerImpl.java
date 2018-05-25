package pt.iscte.es2.datamanager;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.iscte.es2.dao.*;
import pt.iscte.es2.dto.*;
import pt.iscte.es2.jpa.*;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class OptimizationDataManagerImpl implements OptimizationDataManager {

	@Autowired
	private FileUploadDao fileUploadDao;

	@Autowired
	private OptimizationConfigurationDao optimizationConfigurationDao;

	@Autowired
	private OptimizationConfigurationVariablesDao optimizationConfigurationVariablesDao;

	@Autowired
	private OptimizationConfigurationObjectivesDao optimizationConfigurationObjectivesDao;

	@Autowired
	private OptimizationConfigurationAlgorithmsDao optimizationConfigurationAlgorithmsDao;

	@Autowired
	private OptimizationConfigurationRestrictionsDao optimizationConfigurationRestrictionsDao;

	@Autowired
	private OptimizationConfigurationUserSolutionsDao optimizationConfigurationUserSolutionsDao;

	@Autowired
	private OptimizationJobExecutionsDao optimizationJobExecutionsDao;

	@Autowired
	private OptimizationJobSolutionsDao optimizationJobSolutionsDao;

	@Autowired
	private Mapper mapper;

	/**
	 * @see OptimizationDataManager#searchFilePathBySessionId(String)
	 */
	public String searchFilePathBySessionId(String sessionId) {
		List<FileUploadEntity> list = fileUploadDao.findBySessionId(sessionId);
		return list.get(list.size() - 1).getFilePath();
	}

	/**
	 * @see OptimizationDataManager#saveOptimization(OptimizationConfiguration)
	 */
	public OptimizationConfiguration saveOptimization(OptimizationConfiguration optimizationConfiguration) {
		OptimizationConfigurationEntity entity = mapper.map(optimizationConfiguration, OptimizationConfigurationEntity.class);
		OptimizationConfigurationEntity savedOptimizationConfigurationEntity = optimizationConfigurationDao.save(entity);
		return mapper.map(savedOptimizationConfigurationEntity, OptimizationConfiguration.class);
	}

	/**
	 * @see OptimizationDataManager#saveFileUpload(String, String)
	 */
	public FileUpload saveFileUpload(String sessionId, String filePath) {
		FileUploadEntity entity = new FileUploadEntity();
		entity.setSessionId(sessionId);
		entity.setFilePath(filePath);
		FileUploadEntity savedFileUploadEntity = fileUploadDao.saveAndFlush(entity);
		return new FileUpload(savedFileUploadEntity.getId().intValue(), entity.getSessionId(), entity.getFilePath());
	}

	/**
	 * @see OptimizationDataManager#searchOptimizationConfigurationByIdAndEmail(Integer, String)
	 */
	public OptimizationConfiguration searchOptimizationConfigurationByIdAndEmail(Integer id, String email) {
		return mapper.map(optimizationConfigurationDao
			.findByIdAndEmail(id.longValue(), email), OptimizationConfiguration.class);
	}

	/**
	 * @see OptimizationDataManager#searchOptimizationConfigurationByEmail(String)
	 */
	public List<SummaryOptimizationConfiguration> searchOptimizationConfigurationByEmail(String email) {
		List<SummaryOptimizationConfiguration> list = new ArrayList<>();
		optimizationConfigurationDao.findByEmail(email)
			.forEach(entity -> list.add(new SummaryOptimizationConfiguration(
					entity.getId().intValue(), entity.getProblemName(), entity.getDescription(), entity.getCreatedAt())));
		return list;
	}

	/**
	 * @see OptimizationDataManager#saveExecutionOptimizationConfiguration(OptimizationConfiguration)
	 */
	public Integer saveExecutionOptimizationConfiguration(OptimizationConfiguration optimizationConfiguration) {
		OptimizationConfigurationEntity optimizationConfigurationEntity = mapper
			.map(optimizationConfiguration, OptimizationConfigurationEntity.class);
		OptimizationJobExecutionsEntity entity = new OptimizationJobExecutionsEntity(new Date());
		optimizationConfigurationEntity.getOptimizationJobExecutions().add(entity);
		OptimizationConfigurationEntity savedOptimizationConfigurationEntity = optimizationConfigurationDao
			.save(optimizationConfigurationEntity);
		return savedOptimizationConfigurationEntity.getOptimizationJobExecutions()
			.get(savedOptimizationConfigurationEntity.getOptimizationJobExecutions().size()-1).getId().intValue();
	}

	/**
	 * @see OptimizationDataManager#searchOptimizationJobExecutionsById(Integer)
	 */
	public OptimizationJobExecutions searchOptimizationJobExecutionsById(Integer id) {
		Optional<OptimizationJobExecutionsEntity> entity = optimizationJobExecutionsDao.findById(id.longValue());
		if (entity.isPresent()) {
			return mapper.map(entity.get(), OptimizationJobExecutions.class);
		}
		return null;
	}

	/**
	 * @see OptimizationDataManager#saveOptimizationJobSolution(Integer, List, State, String, String)
	 */
	public List<OptimizationJobSolutions> saveOptimizationJobSolution(Integer id,
		List<OptimizationJobSolutions> optimizationJobSolutions, State state, String latexPath, String rPath) {
		Optional<OptimizationJobExecutionsEntity> optimizationJobExecutionsEntity = optimizationJobExecutionsDao
			.findById(id.longValue());
		if (optimizationJobExecutionsEntity.isPresent()) {
			optimizationJobExecutionsEntity.get().setState(state);
			optimizationJobExecutionsEntity.get().setEndDate(new Date());
			if (latexPath.isEmpty()) {
				optimizationJobExecutionsEntity.get().setLatexPath(null);
			} else {
				optimizationJobExecutionsEntity.get().setLatexPath(latexPath);
			}
			if (rPath.isEmpty()) {
				optimizationJobExecutionsEntity.get().setrPath(null);
			} else {
				optimizationJobExecutionsEntity.get().setrPath(rPath);
			}
			List<OptimizationJobSolutionsEntity> solutionsEntities = optimizationJobSolutions
				.stream()
				.map(jobSolution -> mapper.map(jobSolution, OptimizationJobSolutionsEntity.class))
				.collect(Collectors.toList());
			optimizationJobExecutionsEntity.get().setOptimizationJobSolutions(solutionsEntities);
			OptimizationJobExecutionsEntity savedEntity = optimizationJobExecutionsDao
				.saveAndFlush(optimizationJobExecutionsEntity.get());
			return savedEntity.getOptimizationJobSolutions()
				.stream()
				.map(entity -> mapper.map(entity, OptimizationJobSolutions.class))
				.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	/**
	 * @see OptimizationDataManager#updateState(Integer, State)
	 */
	public OptimizationJobExecutions updateState(Integer id, State state) {
		OptimizationJobExecutionsEntity entity = optimizationJobExecutionsDao.getOne(id.longValue());
		entity.setState(state);
		if (state.equals(State.Running)) {
			return mapper.map(optimizationJobExecutionsDao.save(entity), OptimizationJobExecutions.class);
		} else {
			entity.setEndDate(new Date());
			return mapper.map(optimizationJobExecutionsDao.save(entity), OptimizationJobExecutions.class);
		}
	}

	/**
	 * @see OptimizationDataManager#searchOptimizationJobExecutionsByOptimizationConfigurationId(Integer)
	 */
	public List<OptimizationJobExecutions> searchOptimizationJobExecutionsByOptimizationConfigurationId(Integer id) {
		List<OptimizationJobExecutions> executions = new ArrayList<>();
		optimizationConfigurationDao.findById(id.longValue())
			.ifPresent(entity -> {
				executions.addAll(entity.getOptimizationJobExecutions()
					.stream()
					.map(executionEntity -> mapper.map(executionEntity, OptimizationJobExecutions.class))
					.collect(Collectors.toList()));
			});
		return executions;
	}

	/**
	 * @see OptimizationDataManager#searchOptimizationConfigurationByOptimizationJobExecution(OptimizationJobExecutions)
	 */
	public OptimizationConfiguration searchOptimizationConfigurationByOptimizationJobExecution(
		OptimizationJobExecutions optimizationJobExecution) {
		return mapper.map(
			optimizationConfigurationDao.findByOptimizationJobExecutions(
				mapper.map(optimizationJobExecution, OptimizationJobExecutionsEntity.class)),
			OptimizationConfiguration.class);
	}

	/**
	 * @see OptimizationDataManager#searchLatexPathByExecutionId(Integer)
	 */
	public String searchLatexPathByExecutionId(Integer id) {
		String path = "";
		Optional<OptimizationJobExecutionsEntity> executionEntity = optimizationJobExecutionsDao.findById(id.longValue());
		if (executionEntity.isPresent()) {
			return executionEntity.get().getLatexPath();
		}
		return null;
	}

	/**
	 * @see OptimizationDataManager#searchRPathByExecutionId(Integer)
	 */
	public String searchRPathByExecutionId(Integer id) {
		String path = "";
		Optional<OptimizationJobExecutionsEntity> executionEntity = optimizationJobExecutionsDao.findById(id.longValue());
		if (executionEntity.isPresent()) {
			return executionEntity.get().getrPath();
		}
		return null;
	}
}
