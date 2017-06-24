package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Steps;

public interface StepsRepository extends JpaRepository<Steps, Long> {
	// @Query("select s from Steps s order by s.id asc")
	// public Page<Steps> findByIdAsc(Pageable page);

	public Steps findByName(String name);

	@Query("select s from Steps s where s.id=?1")
	public Steps findById(Long stepId);
}
