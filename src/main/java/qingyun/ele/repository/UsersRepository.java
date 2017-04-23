package qingyun.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Users;

public interface UsersRepository  extends JpaRepository<Users, Long> {
	
	public Users findByUsername(String username);
	public Users findByToken(String token);
	
	 @Query("select u from Users u order by u.id asc")	
	 public Page<Users> findUsers(Pageable page);

}
