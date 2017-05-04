package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.SignWorkflowSteps;

public interface SignWorkflowStepsRepository extends JpaRepository<SignWorkflowSteps, Long> {

	@Query("select s from SignWorkflowSteps s where s.idSignWorkflow=?1 order by s.lvl asc")
	public Page<SignWorkflowSteps> findByIdSignWorkflow(Long idSignWorkflow, Pageable page);

	@Query("select s from SignWorkflowSteps s where s.idSignWorkflow=?1 order by s.lvl asc")
	public List<SignWorkflowSteps> findByIdSignWorkflow(Long idSignWorkflow);

	@Query("select s from SignWorkflowSteps s where s.idSignWorkflow=?1 and s.lvl=?2")
	public List<SignWorkflowSteps> findByIdSignWorkflowAndLvl(Long idSignWorkflow, Long lvl);

}
