package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationVariablesEntity;

import java.util.List;

@Repository
public interface OptimizationConfigurationVariablesDao extends JpaRepository<OptimizationConfigurationVariablesEntity, Long> {

//	/**
//	 * Searchs for all Variables associated with a given OptimizationConfigurationId
//	 *
//	 * @param id
//	 * 			Long
//	 *
//	 * @return List<OptimizationConfigurationVariablesEntity>
//	 */
//	List<OptimizationConfigurationVariablesEntity> findByOptimizationConfigurationId(Long id);
}
