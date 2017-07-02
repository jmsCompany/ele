package qingyun.ele.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.*;
import qingyun.ele.repository.*;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSRoleLocation;
import qingyun.ele.ws.WSRolePage;
import qingyun.ele.ws.WSRolePerms;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class RoleController {

	private static final Log logger = LogFactory.getLog(RoleController.class);
	@Autowired
	private PagesRepository pagesRepository;
	@Autowired
	private RolePagesRepository rolePagesRepository;
	@Autowired
	private SubSubLocationRepository subSubLocationRepository;
	@Autowired
	private RoleLocationRepository roleLocationRepository;
	@Autowired
	private SecurityUtils securityUtils;
	@Autowired
	private UserRoleRepository  userRoleRepository;

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/role/saveUserRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveUserRole(@RequestBody UserRole userRole ) {
		Valid v = new Valid();
		if (userRole.getIdRole() == null) {
			v.setValid(false);
			v.setMsg("角色ID不能为空！");
			return v;
		}
		if (userRole.getIdUser() == null) {
			v.setValid(false);
			v.setMsg("用户ID不能为空！");
			return v;
		}


		if(userRole.getId()==null){ //ID为空 新增操作
          //判断该记录是否已经存在，防止重复提交
			if(null!=userRoleRepository.findrolesByUserIdAndroleId(userRole.getIdUser(),userRole.getIdRole())){
				v.setValid(false);
				v.setMsg("该用户已经添加该角色，不能重复添加！");
				return v;
			}
			userRoleRepository.save(userRole);
		}else {                    //ID不为空，修改操作
			Long id=userRole.getId();
			UserRole unew=userRoleRepository.findOne(id);
			unew.setIdUser(userRole.getIdUser());
			unew.setIdRole(userRole.getIdRole());
			unew.setIsPrim(userRole.getIsPrim());
			userRoleRepository.save(unew);
		}
		v.setValid(true);
		v.setMsg("角色操作成功！");
		return v;
	}

	@RequestMapping(value = "/sys/role/getUserRoles", method = RequestMethod.GET)
	public List<UserRole> getUserRoles(@RequestParam("userId") Long userId) {
		List<UserRole> urs = userRoleRepository.findrolesByUserId(userId);
		return urs;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/role/saveRolePermissions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveRolePermissions(@RequestBody WSRolePerms wsRolePerms) {
		Valid v = new Valid();
		if (wsRolePerms.getIdRole() == null) {
			v.setValid(false);
			v.setMsg("角色ID不能为空！");
			return v;
		}
		/*
		 * { idRole:1, locationList:[{idLocation:3},{idLocation:4}],
		 * pageList:[{idPage:4}{idPage:7}] }
		 */
		roleLocationRepository.delete(roleLocationRepository.findByRoleId(wsRolePerms.getIdRole()));
		rolePagesRepository.delete(rolePagesRepository.findByRoleId(wsRolePerms.getIdRole()));

		for (WSRoleLocation rl : wsRolePerms.getLocationList()) {
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(wsRolePerms.getIdRole());
			id.setIdSubSubLocation(rl.getIdLocation());
			RoleLocations drl = new RoleLocations();
			drl.setId(id);
			roleLocationRepository.save(drl);
		}
		for (WSRolePage rp : wsRolePerms.getPageList()) {
			RolePagesId id = new RolePagesId();
			id.setIdRole(wsRolePerms.getIdRole());
			id.setIdPage(rp.getIdPage());
			RolePages drp = new RolePages();
			drp.setId(id);
			rolePagesRepository.save(drp);
		}

		v.setValid(true);
		return v;

	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/role/findRolePermissions", method = RequestMethod.GET)
	public WSRolePerms findRolePermissions(@RequestParam("roleId") Long roleId) {

		WSRolePerms ws = new WSRolePerms();
		ws.setIdRole(roleId);
		List<WSRolePage> wsRolePages = new ArrayList<WSRolePage>();
		Users sessionUser = securityUtils.getCurrentDBUser();
		for (Pages p : pagesRepository.findAll()) {
			WSRolePage w = new WSRolePage();
			w.setIdPage(p.getId());
			w.setPage(p.getDescr());
			RolePagesId id = new RolePagesId();
			id.setIdPage(p.getId());
			id.setIdRole(roleId);
			RolePages rp = rolePagesRepository.findOne(id);
			if (rp != null || sessionUser.getUsername().equals("admin")) {
				w.setHasPerm(1l);
			} else {
				w.setHasPerm(0l);
			}
			wsRolePages.add(w);
		}

		List<WSRoleLocation> wsRoleLocations = new ArrayList<WSRoleLocation>();
		for (SubSubLocation s : subSubLocationRepository.findByEnabled(1l)) {
			WSRoleLocation w = new WSRoleLocation();
			w.setIdLocation(s.getId());
			w.setLocation(s.getSubLocation().getLocation().getName() + " " + s.getSubLocation().getName() + " "
					+ s.getName());
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(roleId);
			id.setIdSubSubLocation(s.getId());
			RoleLocations rp = roleLocationRepository.findOne(id);
			if (rp != null || sessionUser.getUsername().equals("admin")) {
				w.setHasPerm(1l);
			} else {
				w.setHasPerm(0l);
			}
			wsRoleLocations.add(w);
		}
		ws.setPageList(wsRolePages);
		ws.setLocationList(wsRoleLocations);
		return ws;

	}

	@RequestMapping(value = "/sys/role/pageTable", method = RequestMethod.POST)
	public WSTableData pageTable(@RequestParam("roleId") Long roleId) {

		Users sessionUser = securityUtils.getCurrentDBUser();
		List<String[]> lst = new ArrayList<String[]>();
		for (Pages p : pagesRepository.findAll()) {

			RolePagesId id = new RolePagesId();
			id.setIdPage(p.getId());
			id.setIdRole(roleId);
			RolePages rp = rolePagesRepository.findOne(id);
			String xq = "无";
			if (rp != null) {
				xq = "有";
			}

			String[] d = { "" + p.getId(), p.getName(), xq, "" + p.getId() };
			lst.add(d);
		}
		WSTableData t = new WSTableData();
		t.setData(lst);
		return t;
	}

	@RequestMapping(value = "/sys/role/locationTable", method = RequestMethod.POST)
	public WSTableData locationTable(@RequestParam("roleId") Long roleId) {

		Users sessionUser = securityUtils.getCurrentDBUser();
		List<String[]> lst = new ArrayList<String[]>();

		for (SubSubLocation s : subSubLocationRepository.findByEnabled(1l)) {
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(roleId);
			id.setIdSubSubLocation(s.getId());
			RoleLocations rp = roleLocationRepository.findOne(id);
			String xq = "无";
			if (rp != null) {
				xq = "有";
			}
			String[] d = { "" + s.getId(),
					s.getSubLocation().getLocation().getName() + " " + s.getSubLocation().getName() + " " + s.getName(),
					xq, "" + s.getId() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setData(lst);
		return t;
	}




}
