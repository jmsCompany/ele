package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Query("select i from Loan i where i.idProject=?1")
	public Loan findByIdProject(Long idProject);

}
