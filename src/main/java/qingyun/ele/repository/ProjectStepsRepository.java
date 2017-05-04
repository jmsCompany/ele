package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.ProjectSteps;

public interface ProjectStepsRepository extends JpaRepository<ProjectSteps, Long> {

	@Query("select p from ProjectSteps p where p.customer.id=?1 and p.steps.id=?2")
	public ProjectSteps findByProjectIdAndStepId(Long projectId, Long stepId);

}
