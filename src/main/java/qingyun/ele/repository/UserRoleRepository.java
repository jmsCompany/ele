package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.RolePages;
import qingyun.ele.domain.db.RolePagesId;
import qingyun.ele.domain.db.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query("select r from UserRole r where r.idUser=?1")
	public List<UserRole> findrolesByUserId(Long userId);
	
	
	@Query("select r from UserRole r where r.idUser=?1 and r.idRole=?2")
	public UserRole findrolesByUserIdAndroleId(Long userId, long roleId);

}
