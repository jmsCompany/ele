package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qingyun.ele.domain.db.Pages;
import qingyun.ele.domain.db.RolePages;
import qingyun.ele.domain.db.RolePagesId;

public interface RolePagesRepository  extends JpaRepository<RolePages, RolePagesId> {

//	@Query("select r.pages from RolePages r where r.id.idRole=?1 order by r.id.idPage")
//	public List<Pages> findPagesByRoleId(Long roleId);
	
	
	@Query("select r from RolePages r where r.id.idRole=?1")
	public List<RolePages> findByRoleId(Long roleId);
}
