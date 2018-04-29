package pt.iscte.es2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.es2.jpa.OptimizationJobSolutionsEntity;

@Repository
public interface OptimizationJobSolutionsDao extends JpaRepository<OptimizationJobSolutionsEntity, Long> {
}
