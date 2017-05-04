package qingyun.ele.ws;

import java.util.Date;

public class WSProjectSteps {

	private Long id;
	private Long idProgress;
	private Long userId;
	private Long departmentId;
	private Long statusId;
	private Long stepId;
	private Long customerId;
	private String name;
	private Long forcastDays;
	private Long lastedDays;
	private Date start;
	private Date end;
	private Long actDays;
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProgress() {
		return idProgress;
	}

	public void setIdProgress(Long idProgress) {
		this.idProgress = idProgress;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getForcastDays() {
		return forcastDays;
	}

	public void setForcastDays(Long forcastDays) {
		this.forcastDays = forcastDays;
	}

	public Long getLastedDays() {
		return lastedDays;
	}

	public void setLastedDays(Long lastedDays) {
		this.lastedDays = lastedDays;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getActDays() {
		return actDays;
	}

	public void setActDays(Long actDays) {
		this.actDays = actDays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
