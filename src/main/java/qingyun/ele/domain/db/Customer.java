package qingyun.ele.domain.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "customer", catalog = "ele")
public class Customer implements java.io.Serializable {

	private Long id;
	private SubSubLocation subSubLocation;
	private Dic dic;
	private String name;
	private String address;
	private String code;
	private String project;
	private Date start;
	private Date end;
	private Long deleted;
	private Date creationTime;
	private Long creator;
	private Long saleMan;
	private Float actVol;
	private Float unitPrice;
	private Float unitCost;
	private Long process;
	private Float saleCost;
	private Float managementCost;
	private Float agentCost;
	private Float devCost;
	private Float monthLoan;
	private Long durationLoan;
	private Float monthIncome;
	private Float netProfit;
	private String loanTime;
	private Date soCreationTime;
	private Long currentStep;
	private Float mainternanceCost;
	private Float percent;
	private String content;
	//当前步骤
	private Long currStep;
	//0 保存  1 提交签名
	private Long commit;
	
	private String mobile;

	private Set<So> sos = new HashSet<So>(0);
	private Set<ProjectSteps> projectStepses = new HashSet<ProjectSteps>(0);

	public Customer() {}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sub_sub_loc")
	public SubSubLocation getSubSubLocation() {
		return this.subSubLocation;
	}

	public void setSubSubLocation(SubSubLocation subSubLocation) {
		this.subSubLocation = subSubLocation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	public Dic getDic() {
		return this.dic;
	}

	public void setDic(Dic dic) {
		this.dic = dic;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "code", length = 64)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "project", length = 64)
	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start", length = 19)
	public Date getStart() {
		return this.start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end", length = 19)
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<So> getSos() {
		return this.sos;
	}

	public void setSos(Set<So> sos) {
		this.sos = sos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<ProjectSteps> getProjectStepses() {
		return this.projectStepses;
	}

	public void setProjectStepses(Set<ProjectSteps> projectStepses) {
		this.projectStepses = projectStepses;
	}

	@Column(name = "deleted")
	public Long getDeleted() {
		return deleted;
	}

	public void setDeleted(Long deleted) {
		this.deleted = deleted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time", length = 19)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "sale_cosyt", precision = 12, scale = 0)
	public Float getSaleCost() {
		return saleCost;
	}

	public void setSaleCost(Float saleCost) {
		this.saleCost = saleCost;
	}

	@Column(name = "management_cost", precision = 12, scale = 0)
	public Float getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(Float managementCost) {
		this.managementCost = managementCost;
	}

	@Column(name = "agent_cost", precision = 12, scale = 0)
	public Float getAgentCost() {
		return agentCost;
	}

	public void setAgentCost(Float agentCost) {
		this.agentCost = agentCost;
	}

	@Column(name = "dev_cost", precision = 12, scale = 0)
	public Float getDevCost() {
		return devCost;
	}

	public void setDevCost(Float devCost) {
		this.devCost = devCost;
	}

	@Column(name = "month_loan", precision = 12, scale = 0)
	public Float getMonthLoan() {
		return monthLoan;
	}

	public void setMonthLoan(Float monthLoan) {
		this.monthLoan = monthLoan;
	}

	@Column(name = "duration_loan")
	public Long getDurationLoan() {
		return durationLoan;
	}

	public void setDurationLoan(Long durationLoan) {
		this.durationLoan = durationLoan;
	}

	@Column(name = "month_income")
	public Float getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(Float monthIncome) {
		this.monthIncome = monthIncome;
	}

	@Column(name = "net_profit")
	public Float getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Float netProfit) {
		this.netProfit = netProfit;
	}

	@Column(name = "loan_time", length = 64)
	public String getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}

	@Column(name = "sales_man")
	public Long getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(Long saleMan) {
		this.saleMan = saleMan;
	}

	@Column(name = "act_vol", precision = 12, scale = 0)
	public Float getActVol() {
		return actVol;
	}

	public void setActVol(Float actVol) {
		this.actVol = actVol;
	}

	@Column(name = "unit_price", precision = 12, scale = 0)
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "unit_cost", precision = 12, scale = 0)
	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name = "process")
	public Long getProcess() {
		return process;
	}

	public void setProcess(Long process) {
		this.process = process;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "so_creation_time", length = 19)
	public Date getSoCreationTime() {
		return soCreationTime;
	}

	public void setSoCreationTime(Date soCreationTime) {
		this.soCreationTime = soCreationTime;
	}

	@Column(name = "current_step")
	public Long getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Long currentStep) {
		this.currentStep = currentStep;
	}
	
	@Column(name = "mainternance_cost", precision = 12, scale = 0)
	public Float getMainternanceCost() {
		return mainternanceCost;
	}

	public void setMainternanceCost(Float mainternanceCost) {
		this.mainternanceCost = mainternanceCost;
	}
	
	@Column(name = "percent", precision = 12, scale = 0)
	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}
	
	
	@Column(name = "content", length = 16777216)
	public String getContent() {

		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "curr_step")
	public Long getCurrStep() {
		return this.currStep;
	}

	public void setCurrStep(Long currStep) {
		this.currStep = currStep;
	}

	@Column(name = "commit")
	public Long getCommit() {
		return this.commit;
	}

	public void setCommit(Long commit) {
		this.commit = commit;
	}

	@Column(name = "mobile", length = 45)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
