package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qingyun.ele.domain.db.Location;
import qingyun.ele.domain.db.SubLocation;
import qingyun.ele.domain.db.SubSubLocation;

public interface SubLocationRepository  extends JpaRepository<SubLocation, Long> {
	
	@Query("select s from SubLocation s where s.location.id=?1")
	public List<SubLocation> findByLocationId(Long locationId);
	
	@Query("select s from SubLocation s where s.location.id=?1 and s.enabled=?2")
	public List<SubLocation> findByLocationIdAndEnabled(Long locationId, long enabled);

}
