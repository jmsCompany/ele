package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.SignWorkflow;

public interface SignWorkflowRepository  extends JpaRepository<SignWorkflow, Long> {
	
  public SignWorkflow findByName(String name);
}
