package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qingyun.ele.domain.db.Pages;
import qingyun.ele.domain.db.RoleLocations;
import qingyun.ele.domain.db.RoleLocationsId;
import qingyun.ele.domain.db.RolePages;
import qingyun.ele.domain.db.RolePagesId;
import qingyun.ele.domain.db.SubSubLocation;

public interface RoleLocationRepository  extends JpaRepository<RoleLocations, RoleLocationsId> {

//	@Query("select r.subSubLocation from RoleLocations r where r.id.idRole=?1 order by r.id.idSubSubLocation")
//	public List<SubSubLocation> findBySubSubLocationsRoleId(Long roleId);
	
	
	@Query("select r from RoleLocations r where r.id.idRole=?1")
	public List<RoleLocations> findByRoleId(Long roleId);
}
