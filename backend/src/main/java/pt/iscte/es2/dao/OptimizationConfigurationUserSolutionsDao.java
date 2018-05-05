package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationRestrictionsEntity;
import pt.iscte.es2.jpa.OptimizationConfigurationUserSolutionsEntity;

import java.util.List;

@Repository
public interface OptimizationConfigurationUserSolutionsDao extends JpaRepository<OptimizationConfigurationUserSolutionsEntity, Long> {

	/**
	 * Searchs for all UserSolutions associated with a given OptimizationConfigurationId
	 *
	 * @param id
	 * 			Long
	 *
	 * @return List<OptimizationConfigurationUserSolutionsEntity>
	 */
	List<OptimizationConfigurationUserSolutionsEntity> findByOptimizationConfigurationId(Long id);
}
