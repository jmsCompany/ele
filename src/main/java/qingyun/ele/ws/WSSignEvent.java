package qingyun.ele.ws;

import java.util.Date;

public class WSSignEvent implements java.io.Serializable {

	private Long id;
	private String remark;
	private Long idSignWorkflowSteps;
	private String signWorkflowSteps;
	private Long idEvent;
	private Long status;
	private String statusName;
	private SignStatusEnum signStatusEnum;
	private Date signTime;
	private Long editable;
	private Long lvl;
	private Long idSignatory;
	private Long idDepartment;
	private String signatory;
	private String department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getIdSignWorkflowSteps() {
		return idSignWorkflowSteps;
	}

	public void setIdSignWorkflowSteps(Long idSignWorkflowSteps) {
		this.idSignWorkflowSteps = idSignWorkflowSteps;
	}

	public String getSignWorkflowSteps() {
		return signWorkflowSteps;
	}

	public void setSignWorkflowSteps(String signWorkflowSteps) {
		this.signWorkflowSteps = signWorkflowSteps;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public SignStatusEnum getSignStatusEnum() {
		return signStatusEnum;
	}

	public void setSignStatusEnum(SignStatusEnum signStatusEnum) {
		this.signStatusEnum = signStatusEnum;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Long getEditable() {
		return editable;
	}

	public void setEditable(Long editable) {
		this.editable = editable;
	}

	public Long getLvl() {
		return lvl;
	}

	public void setLvl(Long lvl) {
		this.lvl = lvl;
	}

	public Long getIdSignatory() {
		return idSignatory;
	}

	public void setIdSignatory(Long idSignatory) {
		this.idSignatory = idSignatory;
	}

	public Long getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Long idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getSignatory() {
		return signatory;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
