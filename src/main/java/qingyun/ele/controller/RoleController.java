package qingyun.ele.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.Pages;
import qingyun.ele.domain.db.RoleLocations;
import qingyun.ele.domain.db.RoleLocationsId;
import qingyun.ele.domain.db.RolePages;
import qingyun.ele.domain.db.RolePagesId;
import qingyun.ele.domain.db.SignWorkflow;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.PagesRepository;
import qingyun.ele.repository.RoleLocationRepository;
import qingyun.ele.repository.RolePagesRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSRoleLocation;
import qingyun.ele.ws.WSRolePage;
import qingyun.ele.ws.WSRolePerms;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly=true)
public class RoleController {
	
	private static final Log logger = LogFactory.getLog(RoleController.class);
	@Autowired private PagesRepository pagesRepository;
	@Autowired private RolePagesRepository rolePagesRepository;
	@Autowired private SubSubLocationRepository subSubLocationRepository;
	@Autowired private RoleLocationRepository roleLocationRepository;
	@Autowired private SecurityUtils securityUtils; 
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/role/saveRolePermissions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveRolePermissions(@RequestBody WSRolePerms wsRolePerms) {
		Valid v = new Valid();
		if (wsRolePerms.getIdRole()== null) {
			v.setValid(false);
			v.setMsg("角色ID不能为空！");
			return v;
		}
		/*
		 * {
		 *   idRole:1,
		 *   locationList:[{idLocation:3},{idLocation:4}],
		 *   pageList:[{idPage:4}{idPage:7}]
		 * }
		 */
		roleLocationRepository.delete(roleLocationRepository.findByRoleId(wsRolePerms.getIdRole()));
		rolePagesRepository.delete(rolePagesRepository.findByRoleId(wsRolePerms.getIdRole()));
		
		for(WSRoleLocation rl: wsRolePerms.getLocationList())
		{
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(wsRolePerms.getIdRole());
			id.setIdSubSubLocation(rl.getIdLocation());
			RoleLocations drl = new RoleLocations();
			drl.setId(id);
			roleLocationRepository.save(drl);
		}
		for(WSRolePage rp: wsRolePerms.getPageList())
		{
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
		for(Pages p: pagesRepository.findAll())
		{
			WSRolePage w = new WSRolePage();
			w.setIdPage(p.getId());
			w.setPage(p.getDescr());
			RolePagesId id = new RolePagesId();
			id.setIdPage(p.getId());
			id.setIdRole(roleId);
			RolePages rp = rolePagesRepository.findOne(id);
			if(rp!=null||sessionUser.getUsername().equals("admin"))
			{
				w.setHasPerm(1l);
			}
			else
			{
				w.setHasPerm(0l);
			}
			wsRolePages.add(w);
		}

		
		List<WSRoleLocation> wsRoleLocations = new ArrayList<WSRoleLocation>();
		for(SubSubLocation s: subSubLocationRepository.findByEnabled(1l))
		{
			WSRoleLocation w = new WSRoleLocation();
			w.setIdLocation(s.getId());
			w.setLocation(s.getSubLocation().getLocation().getName() +" "+s.getSubLocation().getName() +" " +s.getName());
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(roleId);
			id.setIdSubSubLocation(s.getId());
			RoleLocations rp = roleLocationRepository.findOne(id);
			if(rp!=null||sessionUser.getUsername().equals("admin"))
			{
				w.setHasPerm(1l);
			}
			else
			{
				w.setHasPerm(0l);
			}
			wsRoleLocations.add(w);
		}
		ws.setPageList(wsRolePages);
		ws.setLocationList(wsRoleLocations);
		return ws;

	}
	
	
	@RequestMapping(value="/sys/role/pageTable", method=RequestMethod.POST)
	public WSTableData pageTable(
			@RequestParam("roleId") Long roleId) 
	{
		
		Users sessionUser = securityUtils.getCurrentDBUser();
		List<String[]> lst = new ArrayList<String[]>();
		for(Pages p: pagesRepository.findAll())
		{
			
			RolePagesId id = new RolePagesId();
			id.setIdPage(p.getId());
			id.setIdRole(roleId);
			RolePages rp = rolePagesRepository.findOne(id);
			String xq = "无";
			if(rp!=null)
			{
				xq ="有";
			}
	
			String[] d = {
					""+p.getId(),
					p.getName(),
					xq,
					""+p.getId()
					};
			lst.add(d);
		}
		WSTableData t = new WSTableData();
	    t.setData(lst);
	    return t;
	}
	
	
	
	@RequestMapping(value="/sys/role/locationTable", method=RequestMethod.POST)
	public WSTableData locationTable(
			@RequestParam("roleId") Long roleId) 
	{
		
		Users sessionUser = securityUtils.getCurrentDBUser();
		List<String[]> lst = new ArrayList<String[]>();
		
		for(SubSubLocation s: subSubLocationRepository.findByEnabled(1l))
		{
			RoleLocationsId id = new RoleLocationsId();
			id.setIdRole(roleId);
			id.setIdSubSubLocation(s.getId());
			RoleLocations rp = roleLocationRepository.findOne(id);
			String xq = "无";
			if(rp!=null)
			{
				xq ="有";
			}
			String[] d = {
					""+s.getId(),
					s.getSubLocation().getLocation().getName() +" "+s.getSubLocation().getName() +" " +s.getName(),
					xq,
					""+s.getId()
					};
			lst.add(d);
		}
		
	
		WSTableData t = new WSTableData();
	    t.setData(lst);
	    return t;
	}
	
	
	

}
