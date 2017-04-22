package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA


import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Dic generated by hbm2java
 */
@Entity
@Table(name="dic"
    ,catalog="ele"
)
public class Dic  implements java.io.Serializable {


     private Long id;
     private DicDic dicDic;
     private String code;
     private String descr;
     private Set<Users> usersesForDepartment = new HashSet<Users>(0);
     private Set<Users> usersesForPos = new HashSet<Users>(0);
     private Set<Users> usersesForEmpStatus = new HashSet<Users>(0);
     private Set<ProjectSteps> projectStepsesForDepartment = new HashSet<ProjectSteps>(0);
     private Set<SubSubLocation> subSubLocations = new HashSet<SubSubLocation>(0);
     private Set<Customer> customers = new HashSet<Customer>(0);
     private Set<Steps> stepses = new HashSet<Steps>(0);
     private Set<ProjectSteps> projectStepsesForStatus = new HashSet<ProjectSteps>(0);
     private Set<ProjectSteps> projectStepsesForProgress = new HashSet<ProjectSteps>(0);
     private Set<RolePages> rolePageses = new HashSet<RolePages>(0);
     private Set<Users> usersesForRole = new HashSet<Users>(0);

    public Dic() {
    }

	
    public Dic(Long id) {
        this.id = id;
    }
    public Dic(Long id, DicDic dicDic, String code, String descr, Set<Users> usersesForDepartment, Set<Users> usersesForPos, Set<Users> usersesForEmpStatus, Set<ProjectSteps> projectStepsesForDepartment, Set<SubSubLocation> subSubLocations, Set<Customer> customers, Set<Steps> stepses, Set<ProjectSteps> projectStepsesForStatus, Set<ProjectSteps> projectStepsesForProgress, Set<RolePages> rolePageses, Set<Users> usersesForRole) {
       this.id = id;
       this.dicDic = dicDic;
       this.code = code;
       this.descr = descr;
       this.usersesForDepartment = usersesForDepartment;
       this.usersesForPos = usersesForPos;
       this.usersesForEmpStatus = usersesForEmpStatus;
       this.projectStepsesForDepartment = projectStepsesForDepartment;
       this.subSubLocations = subSubLocations;
       this.customers = customers;
       this.stepses = stepses;
       this.projectStepsesForStatus = projectStepsesForStatus;
       this.projectStepsesForProgress = projectStepsesForProgress;
       this.rolePageses = rolePageses;
       this.usersesForRole = usersesForRole;
    }
   
     @Id 
     @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_parent")
    public DicDic getDicDic() {
        return this.dicDic;
    }
    
    public void setDicDic(DicDic dicDic) {
        this.dicDic = dicDic;
    }
    
    @Column(name="code", length=64)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="descr", length=128)
    public String getDescr() {
        return this.descr;
    }
    
    public void setDescr(String descr) {
        this.descr = descr;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByDepartment")
    public Set<Users> getUsersesForDepartment() {
        return this.usersesForDepartment;
    }
    
    public void setUsersesForDepartment(Set<Users> usersesForDepartment) {
        this.usersesForDepartment = usersesForDepartment;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByPos")
    public Set<Users> getUsersesForPos() {
        return this.usersesForPos;
    }
    
    public void setUsersesForPos(Set<Users> usersesForPos) {
        this.usersesForPos = usersesForPos;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByEmpStatus")
    public Set<Users> getUsersesForEmpStatus() {
        return this.usersesForEmpStatus;
    }
    
    public void setUsersesForEmpStatus(Set<Users> usersesForEmpStatus) {
        this.usersesForEmpStatus = usersesForEmpStatus;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByDepartment")
    public Set<ProjectSteps> getProjectStepsesForDepartment() {
        return this.projectStepsesForDepartment;
    }
    
    public void setProjectStepsesForDepartment(Set<ProjectSteps> projectStepsesForDepartment) {
        this.projectStepsesForDepartment = projectStepsesForDepartment;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="role_locations", catalog="ele", joinColumns = { 
        @JoinColumn(name="id_role", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="id_sub_sub_location", nullable=false, updatable=false) })
    public Set<SubSubLocation> getSubSubLocations() {
        return this.subSubLocations;
    }
    
    public void setSubSubLocations(Set<SubSubLocation> subSubLocations) {
        this.subSubLocations = subSubLocations;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dic")
    public Set<Customer> getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dic")
    public Set<Steps> getStepses() {
        return this.stepses;
    }
    
    public void setStepses(Set<Steps> stepses) {
        this.stepses = stepses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByStatus")
    public Set<ProjectSteps> getProjectStepsesForStatus() {
        return this.projectStepsesForStatus;
    }
    
    public void setProjectStepsesForStatus(Set<ProjectSteps> projectStepsesForStatus) {
        this.projectStepsesForStatus = projectStepsesForStatus;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByProgress")
    public Set<ProjectSteps> getProjectStepsesForProgress() {
        return this.projectStepsesForProgress;
    }
    
    public void setProjectStepsesForProgress(Set<ProjectSteps> projectStepsesForProgress) {
        this.projectStepsesForProgress = projectStepsesForProgress;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dic")
    public Set<RolePages> getRolePageses() {
        return this.rolePageses;
    }
    
    public void setRolePageses(Set<RolePages> rolePageses) {
        this.rolePageses = rolePageses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dicByRole")
    public Set<Users> getUsersesForRole() {
        return this.usersesForRole;
    }
    
    public void setUsersesForRole(Set<Users> usersesForRole) {
        this.usersesForRole = usersesForRole;
    }




}


