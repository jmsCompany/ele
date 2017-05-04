package qingyun.ele;


import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.UsersRepository;

@Service("tokenUtils")
@Transactional(readOnly = true)
public class TokenUtilsImpl implements TokenUtils {
	private static Log LOGGER = LogFactory.getLog(TokenUtilsImpl.class);
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SecurityUtils securityUtils;

	public static final Long TWO_WEEKS_S = 1209600000l;

	@Override
	public String getToken(MCAUserDetails userDetails) {
		return getToken(userDetails, TWO_WEEKS_S);
	}

	@Override
	public String getToken(MCAUserDetails userDetails, Long expiration) {
		Users user = usersRepository.findOne(userDetails.getUserId());
		if (user != null) {
			return user.getToken();
		}

		else
			return null;
	}

	@Override
	public boolean validate(String token) {
		// LOGGER.debug("find user by token ! " + token);
		Users user = usersRepository.findByToken(token);
		boolean flag = false;
		if (user != null) {

			if (user.getLastLogin().getTime() < new Date().getTime() - TWO_WEEKS_S)
				flag = false;
			else
				flag = true;
		}
		return flag;

	}

	@Override
	@Transactional(readOnly = false)
	public MCAUserDetails getUserFromToken(String token) {
		Users user = usersRepository.findByToken(token);

		user.setLastLogin(new Date());
		user = usersRepository.save(user);

		MCAUserDetails userDetails = new MCAUserDetails();
		userDetails.setEmail(user.getEmail());
		userDetails.setUserId(user.getId());
		userDetails.setMobile(user.getMobile());
		userDetails.setUsername(user.getUsername());
		userDetails.setName(user.getName());
		userDetails.setPassword(user.getPassword());
		if (user.getEnabled().equals(0l)) {
			userDetails.setEnabled(false);
		} else {
			userDetails.setEnabled(true);
		}
		userDetails.setUser(user);
		// securityUtils.g
		userDetails.setAuthorities(securityUtils.getAuthorities(user.getId()));
		return userDetails;
	}

}
