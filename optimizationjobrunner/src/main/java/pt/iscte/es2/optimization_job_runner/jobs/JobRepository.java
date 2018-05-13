package pt.iscte.es2.optimization_job_runner.jobs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

	@Query("select job from Job job where job.state='Ready'")
	List<Job> findAwaiting();

}
