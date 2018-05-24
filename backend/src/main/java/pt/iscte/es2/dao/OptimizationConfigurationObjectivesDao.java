package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationObjectivesEntity;
import pt.iscte.es2.jpa.OptimizationConfigurationVariablesEntity;

import java.util.List;

@Repository
public interface OptimizationConfigurationObjectivesDao extends JpaRepository<OptimizationConfigurationObjectivesEntity, Long> {

//	/**
//	 * Searchs for all Objectives associated with a given OptimizationConfigurationId
//	 *
//	 * @param id
//	 * 			Long
//	 *
//	 * @return List<OptimizationConfigurationObjectivesEntity>
//	 */
//	List<OptimizationConfigurationObjectivesEntity> findByOptimizationConfigurationId(Long id);
}
