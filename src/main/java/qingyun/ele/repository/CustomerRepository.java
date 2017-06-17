package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qingyun.ele.domain.db.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select c from Customer c where (c.name like  %:q% or c.code like  %:q%"
			+ " or c.project like %:q%  or c.address like  %:q% or c.dic.code like  %:q%"
			+ " or c.subSubLocation.name like %:q% or c.subSubLocation.subLocation.name like %:q%"
			+ " or c.subSubLocation.subLocation.location.name like %:q%) and c.deleted=0"
			+ " and c.subSubLocation.id in (select r.id.idSubSubLocation from RoleLocations r where r.id.idRole=:roleId)")
	public Page<Customer> findByQ(@Param("q") String q, @Param("roleId") Long roleId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.subSubLocation.id in (select r.id.idSubSubLocation from RoleLocations r where r.id.idRole=?1)")
	public List<Customer> findAllCustomers(Long roleId);

	@Query("select c from Customer c where c.deleted=0 and c.subSubLocation.id in (select r.id.idSubSubLocation from RoleLocations r where r.id.idRole=?1) and c.dic.id=?2")
	public Page<Customer> findAllCustomersByRoleIdAnsStatusId(Long roleId, Long statusId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.subSubLocation.id in (select r.id.idSubSubLocation from RoleLocations r where r.id.idRole=?1)")
	public Page<Customer> findAllCustomersByRoleId(Long roleId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.id=?1")
	public Page<Customer> findByProjectId(Long projectId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.subSubLocation.id=?1")
	public Page<Customer> findByLocationId(Long locationId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.currentStep=?1 and c.subSubLocation.id in (select r.id.idSubSubLocation from RoleLocations r where r.id.idRole=?2)")
	public Page<Customer> findByStepId(Long stepId, Long roleId, Pageable page);

	@Query("select c from Customer c where c.deleted=0 and c.currentStep=?1 and c.subSubLocation.id=?2")
	public Page<Customer> findByStepIdAndLocationId(Long stepId, Long locationId, Pageable page);

}
