package qingyun.ele.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserProfile;


/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@RestController
@Transactional(readOnly=true)
public class UserController {
	
	@Autowired private UsrService usrService;
	@Autowired private UsersRepository usersRepository;
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@Transactional(readOnly=false)
	@RequestMapping(value="/login", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public WSUserProfile login(@RequestBody WSUser wsUser)  {
		WSUserProfile wsUserProfile = new WSUserProfile();
		
		String token = usrService.login(wsUser.getUsername(), wsUser.getPassword());
		if(token==null)
		{
			wsUserProfile.setValid(false);
			wsUserProfile.setMsg("用户名或密码错误");
			return wsUserProfile;
		}
		Users u =usersRepository.findByUsername(wsUser.getUsername());
		wsUserProfile.setValid(true);
		wsUserProfile.setToken(token);
		wsUserProfile.setUsername(u.getToken());
		logger.debug(" username: " + u.getName());
		return wsUserProfile;
	}
	
	
	@Transactional(readOnly=false)
	@RequestMapping(value="/user/reg", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public WSUser reg(@RequestBody WSUser wsUser)  {
		
		return wsUser;
	}

}
