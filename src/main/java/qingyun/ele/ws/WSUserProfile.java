package qingyun.ele.ws;

import java.util.Date;
import java.util.List;

public class WSUserProfile extends Valid implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idUser;
	private String name;
	private String username;
    private String mobile;
    private String email;
    private String password;
    private Date creationTime;
    private Long enabled;
    private Date lastLogin;
    private String token;
    private List<WSMenu> WSMenuList;
 
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Long getEnabled() {
		return enabled;
	}
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<WSMenu> getWSMenuList() {
		return WSMenuList;
	}
	public void setWSMenuList(List<WSMenu> wSMenuList) {
		WSMenuList = wSMenuList;
	}

  
}
