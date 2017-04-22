package qingyun.ele.ws;

import java.util.Date;
public class WSUser extends Valid implements java.io.Serializable{
	
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
    
    private Long idEmpStatus;
    private Long idRole;
    private Long idDepartment;
    private Long idPos;
    
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
	public Long getIdEmpStatus() {
		return idEmpStatus;
	}
	public void setIdEmpStatus(Long idEmpStatus) {
		this.idEmpStatus = idEmpStatus;
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public Long getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}
	public Long getIdPos() {
		return idPos;
	}
	public void setIdPos(Long idPos) {
		this.idPos = idPos;
	}

  
}
