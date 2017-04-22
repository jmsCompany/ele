package qingyun.ele.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import qingyun.ele.domain.db.Location;
public interface LocationRepository  extends JpaRepository<Location, Long> {
	public List<Location> findByEnabled(Long enabled);
}
