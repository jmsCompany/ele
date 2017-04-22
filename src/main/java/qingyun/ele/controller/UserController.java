package qingyun.ele.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserPassword;
import qingyun.ele.ws.WSUserProfile;

@RestController
@Transactional(readOnly=true)
public class UserController {
	
	@Autowired private UsrService usrService;
	@Autowired private UsersRepository usersRepository;
	@Autowired private DicRepository dicRepository;
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
		//logger.debug(" username: " + u.getName());
		return wsUserProfile;
	}
	
	
	@Transactional(readOnly=false)
	@RequestMapping(value="/user/saveUser", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public WSUser save(@RequestBody WSUser wsUser)  {
		
		if(wsUser.getUsername()==null)
		{
			wsUser.setValid(false);
			wsUser.setMsg("必须设置用户名！");
			return wsUser;
		}

		Users dbUser=usersRepository.findByUsername(wsUser.getUsername());
		Users u;
		if(wsUser.getIdUser()==null&&wsUser.getIdUser().equals(0l))
		{
			if(dbUser!=null)
			{
				wsUser.setValid(false);
				wsUser.setMsg("该用户名已存在！");
				return wsUser;
			}
			u = new Users();
			u.setPassword(new BCryptPasswordEncoder().encode(wsUser.getPassword()));
		  //  u.setPassword(wsUser.getPassword());
		}
		else
		{
			u = usersRepository.findOne(wsUser.getIdUser());
			if(dbUser!=null&&!dbUser.getId().equals(wsUser.getIdUser()))
			{
				wsUser.setValid(false);
				wsUser.setMsg("该用户名已存在！");
				return wsUser;
			}
		}
		u.setUsername(wsUser.getUsername());
		u.setEmail(wsUser.getEmail());
		u.setMobile(wsUser.getMobile());
		u.setDicByEmpStatus(dicRepository.getOne(wsUser.getIdEmpStatus()));
		u.setDicByDepartment(dicRepository.getOne(wsUser.getIdDepartment()));
		u.setDicByPos(dicRepository.getOne(wsUser.getIdPos()));
		u.setDicByRole(dicRepository.getOne(wsUser.getIdRole()));
	    u.setEnabled(wsUser.getEnabled());
	    u.setName(wsUser.getName());
	    usersRepository.save(u);
	    wsUser.setValid(true);
		return wsUser;
	}

	
	@Transactional(readOnly=false)
	@RequestMapping(value="/user/updateUserPassword", method=RequestMethod.POST)
	public Valid updateUserPassword(@RequestBody WSUserPassword wsUserPassword) throws Exception {
		Valid v = new Valid();
		Long userId = wsUserPassword.getIdUser();
		Users u = usersRepository.findOne(userId);
		if(new BCryptPasswordEncoder().matches(wsUserPassword.getPassword(), u.getPassword()))
    	{
			u.setPassword(new BCryptPasswordEncoder().encode(wsUserPassword.getNewPassword()));
			usersRepository.save(u);
			v.setValid(true);
			return v;
    	}
		else
		{
			v.setValid(false);
			v.setMsg("您输入的原密码不对！");
			return v;
		}
	}
	
	

	@Transactional(readOnly=false)
	@RequestMapping(value="/user/updateUserPasswordByAdmin", method=RequestMethod.POST)
	public Valid updateUserPasswordByAdmin(@RequestBody WSUserPassword wsUserPassword) throws Exception {
		Valid v = new Valid();
		Long userId = wsUserPassword.getIdUser();
		Users u = usersRepository.findOne(userId);
		u.setPassword(new BCryptPasswordEncoder().encode(wsUserPassword.getNewPassword()));
		usersRepository.save(u);
		v.setValid(true);
		return v;
	}
	
}
