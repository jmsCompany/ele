package qingyun.ele.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.UsersRepository;

@Service("usrService")
@Transactional(readOnly=true)
public class UsrServiceImpl implements UsrService {
	
	@Autowired private UsersRepository usersRepository;
	
	@Override
	@Transactional(readOnly=false)
	public boolean register(Users usr) {
		String username = usr.getUsername();
		if(isRegisted(username))
			return false;  //已经被注册
		else
		{
			String password = usr.getPassword();
			usr.setPassword(new BCryptPasswordEncoder().encode(password));
			usersRepository.save(usr);
			return true;
		}

	}

	@Override
	public boolean isRegisted(String username) {
		Users usr = usersRepository.findByUsername(username);
		if(usr!=null)
			return true;
		else
			return false;
	}

	@Transactional(readOnly=false)
	public String login(String username, String password)
	{
		String defaultMsg=null;
	    Users user =  usersRepository.findByUsername(username);
	    if(user!=null&&user.getEnabled().longValue()==1l)
	    {
	    //	System.out.println("user is not null");
	    	if(new BCryptPasswordEncoder().matches(password, user.getPassword()))
	    	{
	    		//System.out.println("wrong password!");
	    		user.setLastLogin(new Date());
	    		String token = user.getId()+"__"+new BCryptPasswordEncoder().encode(new Date().toString());
				user.setToken(token);
				usersRepository.save(user);
	    		defaultMsg = user.getToken();
	    	}
	    		
	    }
		return defaultMsg;
		
	}



}
