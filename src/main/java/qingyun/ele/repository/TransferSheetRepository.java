package qingyun.ele.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.TransferSheet;

public interface TransferSheetRepository extends JpaRepository<TransferSheet, Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select i from TransferSheet i where i.idProject=?1")
	public TransferSheet findByIdProject(Long idProject);
	
}
