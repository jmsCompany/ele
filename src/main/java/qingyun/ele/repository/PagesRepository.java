package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Pages;

import java.util.List;

public interface PagesRepository extends JpaRepository<Pages, Long> {
	public Pages findByName(String name);

	@Query("select p from Pages p order by p.seq")
	public List<Pages> findAllOrderBySeq();
}
