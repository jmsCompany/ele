package qingyun.ele.ws;

import java.util.Date;

public class WSProjectStepStatus {

	private Long proStepId;
	private Long stepId;
	private String stepName;
	private Long status;

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Long getProStepId() {
		return proStepId;
	}

	public void setProStepId(Long proStepId) {
		this.proStepId = proStepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		if (status==42){
			this.status=1l;
		}else if (status==43){
			this.status=0l;
		}else{
			this.status = 0l;
		}
	}
}
