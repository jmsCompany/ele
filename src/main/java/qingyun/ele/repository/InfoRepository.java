package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {

	@Query("select i from Info i where i.idProject=?1")
	public Info findByIdProject(Long idProject);
}
