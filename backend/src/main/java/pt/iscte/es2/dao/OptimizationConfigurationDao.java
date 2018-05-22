package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;
import pt.iscte.es2.jpa.OptimizationJobExecutionsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptimizationConfigurationDao extends JpaRepository<OptimizationConfigurationEntity, Long> {

	/**
	 *
	 * @param id
	 * 			Integer
	 * @param email
	 * 			String
	 *
	 * @return OptimizationConfigurationEntity
	 */
	OptimizationConfigurationEntity findByIdAndEmail(Long id, String email);

	/**
	 *
	 * @param email
	 * 			String
	 *
	 * @return OptimizationConfigurationEntity
	 */
	List<OptimizationConfigurationEntity> findByEmail(String email);

	/**
	 *
	 * @param entity
	 * 			OptimizationJobExecutionsEntity
	 *
	 * @return OptimizationConfigurationEntity
	 */
	OptimizationConfigurationEntity findByOptimizationJobExecutions(OptimizationJobExecutionsEntity entity);
}
