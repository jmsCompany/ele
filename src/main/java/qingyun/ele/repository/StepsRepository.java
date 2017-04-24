package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Steps;

public interface StepsRepository  extends JpaRepository<Steps, Long> {
// @Query("select s from Steps s order by s.id asc")	
// public Page<Steps> findByIdAsc(Pageable page);

 public Steps findByName(String name);
}
