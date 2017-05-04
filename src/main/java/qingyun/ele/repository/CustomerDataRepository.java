package qingyun.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.CustomerData;

public interface CustomerDataRepository extends JpaRepository<CustomerData, Long> {

	@Query("select c from CustomerData c order by c.id desc")
	public Page<CustomerData> findByIdDesc(Pageable page);
}
