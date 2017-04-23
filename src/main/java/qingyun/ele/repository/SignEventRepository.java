package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qingyun.ele.domain.db.SignEvent;

public interface SignEventRepository  extends JpaRepository<SignEvent, Long> {
	
   public SignEvent findByIdEventAndIdSignWorkflowSteps(Long idEvent,Long idSignWorkflowSteps);
}
