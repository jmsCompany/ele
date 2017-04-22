package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.SubSubLocation;

public interface SubSubLocationRepository  extends JpaRepository<SubSubLocation, Long> {
	
	@Query("select s from SubSubLocation s where s.subLocation.id=?1")
	public List<SubSubLocation> findBySubLocationId(Long subLocationId);
	
	@Query("select s from SubSubLocation s where s.subLocation.id=?1 and s.enabled=?2")
	public List<SubSubLocation> findBySubLocationIdAndEnabled(Long subLocationId,Long enabled);

	public List<SubSubLocation> findByEnabled(Long enabled);

}
