package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationAlgorithmsEntity;
import pt.iscte.es2.jpa.OptimizationConfigurationObjectivesEntity;

import java.util.List;

@Repository
public interface OptimizationConfigurationAlgorithmsDao extends JpaRepository<OptimizationConfigurationAlgorithmsEntity, Long> {

	/**
	 * Searchs for all Algorithms associated with a given OptimizationConfigurationId
	 *
	 * @param id
	 * 			Long
	 *
	 * @return List<OptimizationConfigurationAlgorithmsEntity>
	 */
	List<OptimizationConfigurationAlgorithmsEntity> findByOptimizationConfigurationId(Long id);
}
