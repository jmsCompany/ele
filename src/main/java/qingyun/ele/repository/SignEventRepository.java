package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.SignEvent;

public interface SignEventRepository extends JpaRepository<SignEvent, Long> {

	public SignEvent findByIdEventAndIdSignWorkflowStepsAndDeleted(Long idEvent, Long idSignWorkflowSteps,Long deleted);

	//@Query("select s from SignEvent s where s.idEvent=?1")
	//public List<SignEvent> findByIdEvent(Long idEvent);
	
	@Query("select s from SignEvent s where s.idEvent=?1 and s.idSignWorkflow=?2 and s.deleted=0 order by s.id")
	public List<SignEvent> findNotDeletedByIdEventAndIdSignWorkflow(Long idEvent,Long idSignWorkflow);
	
	
	
	@Query("select s from SignEvent s where s.idEvent=?1 and s.idSignWorkflow=?2 and s.lvl=?3 and s.deleted=0 order by s.id")
	public List<SignEvent> findNotDeletedByIdEventAndIdSignWorkflowAndLvl(Long idEvent,Long idSignWorkflow,Long lvl);

}
