package qingyun.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Logs;

public interface LogsRepository  extends JpaRepository<Logs, Long> {
	
	 @Query("select l from Logs l order by l.id desc")	
	 public Page<Logs> findByIdDesc(Pageable page);
	
}
