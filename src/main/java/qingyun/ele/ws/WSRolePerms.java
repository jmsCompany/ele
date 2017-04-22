package qingyun.ele.ws;

import java.util.ArrayList;
import java.util.List;

public class WSRolePerms {
	
	private Long idRole;
	private List<WSRoleLocation> locationList = new ArrayList<WSRoleLocation>(0);
	private List<WSRolePage> pageList = new ArrayList<WSRolePage>(0);
	
	
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public List<WSRoleLocation> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<WSRoleLocation> locationList) {
		this.locationList = locationList;
	}
	public List<WSRolePage> getPageList() {
		return pageList;
	}
	public void setPageList(List<WSRolePage> pageList) {
		this.pageList = pageList;
	}
	

}
