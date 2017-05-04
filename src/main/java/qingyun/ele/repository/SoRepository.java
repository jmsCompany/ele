package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.So;

public interface SoRepository extends JpaRepository<So, Long> {

	@Query("select i from So i where i.customer.id=?1")
	public So findByIdProject(Long projectId);
}
