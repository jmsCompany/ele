package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qingyun.ele.domain.db.Pages;

public interface PagesRepository  extends JpaRepository<Pages, Long> {
 public Pages findByName(String name);
}
