package qingyun.ele.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.CodeNum;

public interface CodeNumRepository extends JpaRepository<CodeNum, Long> {
	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select c from CodeNum c where c.id=?1")
	public CodeNum findByIdforUpdate(Long id);
}
