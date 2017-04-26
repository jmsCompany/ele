package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RolePages generated by hbm2java
 */
@Entity
@Table(name="role_locations"
    ,catalog="ele"
)
public class RoleLocations  implements java.io.Serializable {


     private RoleLocationsId id;
     private Users users;
     private SubSubLocation subSubLocation;
     private Long seq;

    public RoleLocations() {
    }

	

   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="idRole", column=@Column(name="id_role", nullable=false) ), 
        @AttributeOverride(name="idSubSubLocation", column=@Column(name="id_sub_sub_location", nullable=false) ) } )
    public RoleLocationsId getId() {
        return this.id;
    }
    
    public void setId(RoleLocationsId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_role", nullable=false, insertable=false, updatable=false)
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_sub_sub_location", nullable=false, insertable=false, updatable=false)
    public SubSubLocation getSubSubLocation() {
        return this.subSubLocation;
    }
    
    public void setSubSubLocation(SubSubLocation subSubLocation) {
        this.subSubLocation = subSubLocation;
    }
    
    @Column(name="seq")
    public Long getSeq() {
        return this.seq;
    }
    
    public void setSeq(Long seq) {
        this.seq = seq;
    }




}


