package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qingyun.ele.domain.db.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
	

}
