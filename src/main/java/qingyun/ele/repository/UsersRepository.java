package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qingyun.ele.domain.db.Users;

public interface UsersRepository  extends JpaRepository<Users, Long> {
	
	public Users findByUsername(String username);
	public Users findByToken(String token);

}
