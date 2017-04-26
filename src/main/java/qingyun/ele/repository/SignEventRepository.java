package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.SignEvent;

public interface SignEventRepository extends JpaRepository<SignEvent, Long> {
  
   public SignEvent findByIdEventAndIdSignWorkflowSteps(Long idEvent,Long idSignWorkflowSteps);
   
   @Query("select s from SignEvent s where s.idEvent=?1 order by s.id desc")
   public List<SignEvent> findLastByIdEventAnd(Long idEvent);

}