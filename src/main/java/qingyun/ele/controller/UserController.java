package qingyun.ele.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.Logs;
import qingyun.ele.domain.db.Pages;
import qingyun.ele.domain.db.RolePages;
import qingyun.ele.domain.db.RolePagesId;
import qingyun.ele.domain.db.UserRole;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.LogsRepository;
import qingyun.ele.repository.PagesRepository;
import qingyun.ele.repository.RolePagesRepository;
import qingyun.ele.repository.UserRoleRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSMenu;
import qingyun.ele.ws.WSSelectObj;
import qingyun.ele.ws.WSTableData;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserPassword;
import qingyun.ele.ws.WSUserProfile;
import qingyun.ele.ws.WSUserRole;

@RestController
@Transactional(readOnly = true)
public class UserController {

	@Autowired
	private UsrService usrService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private DicRepository dicRepository;
	@Autowired
	private PagesRepository pagesRepository;
	@Autowired
	private SecurityUtils securityUtils;
	@Autowired
	private RolePagesRepository rolePagesRepository;
	@Autowired
	private LogsRepository logsRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	private static final Log logger = LogFactory.getLog(UserController.class);

	@Transactional(readOnly = false)
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WSUserProfile login(@RequestBody WSUser wsUser, ServletRequest req, ServletResponse res) {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		WSUserProfile wsUserProfile = new WSUserProfile();

		String token = usrService.login(wsUser.getUsername(), wsUser.getPassword());
		if (token == null) {
			wsUserProfile.setValid(false);
			wsUserProfile.setMsg("用户名或密码错误");
			return wsUserProfile;
		}
		Users u = usersRepository.findByUsername(wsUser.getUsername());
		wsUserProfile.setValid(true);
		wsUserProfile.setToken(token);
		wsUserProfile.setUsername(u.getUsername());
		// logger.debug(" username: " + u.getName());
		if (u.getDicByDepartment() != null) {
			wsUserProfile.setDepartment(u.getDicByDepartment().getCode());
		}

		List<Pages> appList = pagesRepository.findAllOrderBySeq();

		List<WSMenu> WSMenuList = new ArrayList<WSMenu>();
		// Dic role = u.getDicByRole();
		for (Pages a : appList) {
			RolePages rp = null;
			if (u != null) {
				RolePagesId id = new RolePagesId();
				id.setIdPage(a.getId());
				id.setIdRole(u.getId());
				rp = rolePagesRepository.findOne(id);
			}
			if (rp != null || u.getUsername().equals("admin") || u.getUsername().equals("test")) {
				WSMenu item = new WSMenu();
				item.setGroup(a.getGroups().trim());
				item.setName(a.getName().trim());
				item.setId(a.getId());
				item.setUrl(a.getUrl().trim());
				WSMenuList.add(item);
			}

		}

		Logs log = new Logs();
		log.setIp(request.getRemoteAddr());
		log.setTime(new Date());
		log.setUrl(request.getRequestURI());
		log.setUsers(u);
		// logger.debug("save logs " + request.getRemoteAddr() +" url: " +
		// request.getRequestURI());
		logsRepository.save(log);
		wsUserProfile.setIdUser(u.getId());
		wsUserProfile.setName(u.getName());
		wsUserProfile.setWSMenuList(WSMenuList);
		// logger.debug("return userProfile: " + wsUserProfile.getToken());
		return wsUserProfile;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/user/saveUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WSUser save(@RequestBody WSUser wsUser) {

		if (wsUser.getUsername() == null) {
			wsUser.setValid(false);
			wsUser.setMsg("必须设置用户名！");
			return wsUser;
		}
		if (wsUser.getUsername().equals("admin")) {
			wsUser.setValid(false);
			wsUser.setMsg("系统用户，不允许被修改！");
			return wsUser;
		}

		// logger.debug("username:" + wsUser.getUsername() +"userid: " +
		// wsUser.getIdUser());
		//
		Users u;
		// create new
		if (wsUser.getIdUser() == null || wsUser.getIdUser().equals(0l)) {
			Users dbUser = usersRepository.findByUsername(wsUser.getUsername());
			if (dbUser != null) {
				wsUser.setValid(false);
				wsUser.setMsg("该用户名已存在！");
				return wsUser;
			}

			u = new Users();
			// logger.debug("create new:" + wsUser.getUsername() +"userid: " +
			// wsUser.getIdUser());
			u.setPassword(new BCryptPasswordEncoder().encode(wsUser.getUsername()));
			// u.setPassword(wsUser.getPassword());
			// logger.debug("new:");
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
		} else {

			Users dbUser = usersRepository.findByUsername(wsUser.getUsername());
			u = usersRepository.findOne(wsUser.getIdUser());
			if (dbUser != null && !dbUser.getId().equals(wsUser.getIdUser())) {
				wsUser.setValid(false);
				wsUser.setMsg("该用户名已存在！");
				return wsUser;
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
		}

		wsUser.setValid(true);
		return wsUser;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/user/updateUserPassword", method = RequestMethod.POST)
	public Valid updateUserPassword(@RequestBody WSUserPassword wsUserPassword) throws Exception {
		Valid v = new Valid();
		Long userId = wsUserPassword.getIdUser();
		Users u = usersRepository.findOne(userId);
		if (new BCryptPasswordEncoder().matches(wsUserPassword.getPassword(), u.getPassword())) {
			u.setPassword(new BCryptPasswordEncoder().encode(wsUserPassword.getNewPassword()));
			usersRepository.save(u);
			v.setValid(true);
			return v;
		} else {
			v.setValid(false);
			v.setMsg("您输入的原密码不对！");
			return v;
		}
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/user/updateUserPasswordByAdmin", method = RequestMethod.POST)
	public Valid updateUserPasswordByAdmin(@RequestBody WSUserPassword wsUserPassword) throws Exception {
		Valid v = new Valid();

		Long userId = wsUserPassword.getIdUser();
		Users u = usersRepository.findOne(userId);
		u.setPassword(new BCryptPasswordEncoder().encode(wsUserPassword.getNewPassword()));
		usersRepository.save(u);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/user/deleteUsers", method = RequestMethod.GET)
	public Valid deleteUsers(@RequestParam("username") String username) {

		Valid v = new Valid();
		Users u = usersRepository.findByUsername(username);
		if (u == null) {
			v.setValid(false);
			v.setMsg("不能找到此用户 Id:");
			return v;
		}
		if (u.getUsername().equals("admin")) {
			v.setValid(false);
			v.setMsg("系统用户不能被删除！");
			return v;
		}
		u.setEnabled(0l);
		usersRepository.save(u);
		v.setValid(true);
		return v;

	}

	@RequestMapping(value = "/sys/user/usersTable", method = RequestMethod.POST)
	public WSTableData usersTable(@RequestParam Integer start, @RequestParam Integer draw,
			@RequestParam Integer length) {

		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<Users> users = usersRepository.findUsers(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for (Users w : users.getContent()) {
			Dic department = w.getDicByDepartment();
			String sd = (department == null) ? "" : department.getCode();
			Dic position = w.getDicByPos();
			String sp = (position == null) ? "" : position.getCode();
			Dic role = w.getDicByRole();
			String sr = (role == null) ? "" : role.getCode();
			Dic status = w.getDicByEmpStatus();
			String ss = (status == null) ? "" : status.getCode();
			String enabled = w.getEnabled().equals(1l) ? "有效" : "无效";
			String email = (w.getEmail() == null) ? "" : w.getEmail();
			String mobile = (w.getMobile() == null) ? "" : w.getMobile();
			String[] d = { "" + w.getId(), w.getUsername(), w.getName(), email, mobile, sd, sp, sr, ss, enabled,
					"" + w.getId() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) users.getTotalElements());
		t.setRecordsFiltered((int) users.getTotalElements());
		t.setData(lst);
		return t;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/user/findUserById", method = RequestMethod.GET)
	public WSUser findUserById(@RequestParam Long userId) {
		WSUser ws = new WSUser();
		Users u = usersRepository.findOne(userId);
		ws.setLastLogin(u.getLastLogin());
		ws.setEmail(u.getEmail());
		ws.setEnabled(u.getEnabled());
		ws.setIdUser(u.getId());
		if (u.getDicByDepartment() != null) {
			ws.setIdDepartment(u.getDicByDepartment().getId());
		}
		if (u.getDicByEmpStatus() != null) {
			ws.setIdEmpStatus(u.getDicByEmpStatus().getId());
		}
		if (u.getDicByRole() != null) {
			ws.setIdRole(u.getDicByRole().getId());
		}
		if (u.getDicByPos() != null) {
			ws.setIdPos(u.getDicByPos().getId());
		}
		ws.setMobile(u.getMobile());
		ws.setValid(true);
		return ws;
	}

	@RequestMapping(value = "/check/jmstoken", method = RequestMethod.GET)
	public Valid checkToken(@RequestParam("jmstoken") String jmstoken) throws Exception {
		Boolean returnVal = usrService.checkToken(jmstoken);
		Valid valid = new Valid();
		valid.setValid(returnVal);
		return valid;
	}

	@RequestMapping(value = "/sys/user/userSelects", method = RequestMethod.GET)
	public List<WSSelectObj> userSelects() {
		List<WSSelectObj> ws = new ArrayList<WSSelectObj>();
		WSSelectObj w1 = new WSSelectObj("", "请选择员工");
		ws.add(w1);
		for (Users u : usersRepository.findByEnabled(1l)) {
			WSSelectObj w = new WSSelectObj(""+u.getId(), u.getName());
			ws.add(w);
		}
		return ws;
	}
	
	

	@RequestMapping(value = "/sys/user/userRoles", method = RequestMethod.GET)
	public List<WSUserRole> userRoles(@RequestParam("userId") Long userId) {
		List<WSUserRole> ws = new ArrayList<WSUserRole>();
		List <Dic> dics = dicRepository.findByDicDicId(5l);
		
		for (Dic u : dics) {
			WSUserRole w = new WSUserRole();
			w.setId(u.getId());
			w.setName(u.getCode());
			//todo:
			UserRole ur = userRoleRepository.findrolesByUserIdAndroleId(userId, w.getId());
			if(ur ==null)
			{
				w.setIsSelected(1l);
			}
			else
			{
				w.setIsSelected(0l);
			}
			ws.add(w);
		}
		return ws;
	}
	
	
}
