package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationObjectivesEntity;
import pt.iscte.es2.jpa.OptimizationConfigurationRestrictionsEntity;

import java.util.List;

@Repository
public interface OptimizationConfigurationRestrictionsDao extends JpaRepository<OptimizationConfigurationRestrictionsEntity, Long> {

	/**
	 * Searchs for all Restrictions associated with a given OptimizationConfigurationId
	 *
	 * @param id
	 * 			Long
	 *
	 * @return List<OptimizationConfigurationRestrictionsEntity>
	 */
	List<OptimizationConfigurationRestrictionsEntity> findByOptimizationConfigurationId(Long id);
}
