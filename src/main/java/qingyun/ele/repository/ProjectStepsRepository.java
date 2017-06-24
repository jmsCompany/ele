package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.ProjectSteps;

public interface ProjectStepsRepository extends JpaRepository<ProjectSteps, Long> {

	@Query("select p from ProjectSteps p where p.customer.id=?1 and p.steps.id=?2")
	public ProjectSteps findByProjectIdAndStepId(Long projectId, Long stepId);

	@Query("select p from ProjectSteps p where p.start is not null and p.end is null and (p.dE is null or p.dME is null)")
	public List<ProjectSteps> scanDelaySteps();

	@Query("select p from ProjectSteps p where p.customer.id=?1 order by p.steps.id")
	public List<ProjectSteps> findByProjectId(Long projectId);

	@Query("select p from ProjectSteps p where p.id=?1")
	public ProjectSteps findById(Long proStepId);
}
