package qingyun.ele;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.UsersRepository;

@Service("userDetailService")
public class MCAUserDetailService implements Serializable, UserDetailsService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsersRepository usersRepository;

	// @Autowired private SecurityUtils securityUtils;
	@Override
	@Transactional(readOnly = true)

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByUsername(username);
		if (user == null) {

			throw new UsernameNotFoundException("Could not find user " + username);
		}
		MCAUserDetails userDetails = new MCAUserDetails();
		userDetails.setUsername(user.getUsername());
		userDetails.setEmail(user.getEmail());
		if (user.getEnabled().equals(0l)) {
			userDetails.setEnabled(false);
		} else {
			userDetails.setEnabled(true);
		}

		userDetails.setUserId(user.getId());
		userDetails.setAuthorities(null);
		userDetails.setPassword(user.getPassword());
		userDetails.setMobile(user.getMobile());
		return userDetails;
	}

}