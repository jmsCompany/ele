package qingyun.ele.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select i from Loan i where i.idProject=?1")
	public Loan findByIdProject(Long idProject);

}
