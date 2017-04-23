package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Dic generated by hbm2java
 */
@Entity
@Table(name="sign_event"
    ,catalog="ele"
)
public class SignEvent  implements java.io.Serializable {

     private Long id;
     private String remark;
	 private Long idSignWorkflowSteps;
     private Long idEvent;
     private Long status;
     private Date signTime;
    
    public SignEvent() {
    }

	
    public SignEvent(Long id) {
        this.id = id;
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

    @Column(name="remark", length=64)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="id_sign_workflow_steps")
    public Long getIdSignWorkflowSteps() {
		return idSignWorkflowSteps;
	}


	public void setIdSignWorkflowSteps(Long idSignWorkflowSteps) {
		this.idSignWorkflowSteps = idSignWorkflowSteps;
	}

	 @Column(name="id_event")
	public Long getIdEvent() {
		return idEvent;
	}


	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}


	 @Column(name="status")
	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}



	 @Temporal(TemporalType.TIMESTAMP)
     @Column(name="sign_time", length=19)
	public Date getSignTime() {
		return signTime;
	}


	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
    

}


