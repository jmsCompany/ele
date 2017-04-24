package qingyun.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qingyun.ele.domain.db.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

	 @Query("select c from Customer c where c.name like  %:q% or c.code like  %:q%"
	 		+ " or c.project like %:q%  or c.address like  %:q% or c.dic.code like  %:q%"
	 		+ " or c.subSubLocation.name like %:q% or c.subSubLocation.subLocation.name like %:q%"
	 		+ " or c.subSubLocation.subLocation.location.name like %:q%")	
	 public Page<Customer> findByQ(@Param("q")String q,Pageable page);

}
