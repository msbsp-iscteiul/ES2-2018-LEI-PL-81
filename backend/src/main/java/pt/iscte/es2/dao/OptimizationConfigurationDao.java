package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationConfigurationEntity;

import java.util.List;

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
}
