package qingyun.ele.domain.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


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
	//经度
	private Float lng;
	//纬度
	private Float lat;
	//图片
	private String pic;

	private String alertEmail;




	//新增c1到c64字段

	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String c6;
	private String c7;
	private String c8;
	private String c9;
	private String c10;
	private String c11;
	private String c12;
	private String c13;
	private String c14;
	private String c15;
	private String c16;
	private String c17;
	private String c18;
	private String c19;
	private String c20;
	private String c21;
	private String c22;
	private String c23;
	private String c24;
	private String c25;
	private String c26;
	private String c27;
	private String c28;
	private String c29;
	private String c30;
	private String c31;
	private String c32;
	private String c33;
	private String c34;
	private String c35;
	private String c36;
	private String c37;
	private String c38;
	private String c39;
	private String c40;
	private String c41;
	private String c42;
	private String c43;
	private String c44;
	private String c45;
	private String c46;
	private String c47;
	private String c48;
	private String c49;
	private String c50;
	private String c51;
	private String c52;
	private String c53;
	private String c54;
	private String c55;
	private String c56;
	private String c57;
	private String c58;
	private String c59;
	private String c60;
	private String c61;
	private String c62;
	private String c63;
	private String c64;


    //新增U1到U12字段
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String p5;
	private String p6;
	private String p7;
	private String p8;
	private String p9;
	private String p10;
	private String p11;
	private String p12;
    //新增9个字段
	private  String intent;
	private  String type;
	private  String kind;
	private  String how;
	private  String inn;
	private  String inkind;
	private  String item;
	private  String item1;
	private  String ni;
	private  String tele;

	private Set<So> sos = new HashSet<So>(0);
	private Set<ProjectSteps> projectStepses = new HashSet<ProjectSteps>(0);

	private  Long idSubSubLocation;
	@Column(name = "idSubSubLocation", length = 64)
	public Long getIdSubSubLocation() {
		return idSubSubLocation;
	}

	public void setIdSubSubLocation(Long idSubSubLocation) {
		this.idSubSubLocation = idSubSubLocation;
	}


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

	@Column(name = "lng",precision = 12,scale = 0)
	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	@Column(name = "lat",precision = 12,scale = 0)
	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}
	@Column(name = "pic",length = 128)
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	@Column(name = "alert_email",length = 128)
	public String getAlertEmail() {
		return alertEmail;
	}

	public void setAlertEmail(String alertEmail) {
		this.alertEmail = alertEmail;
	}

	@Column(name = "c1", length = 64)
	public String getC1() {
		return this.c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	@Column(name = "c2", length = 64)
	public String getC2() {
		return this.c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	@Column(name = "c3", length = 64)
	public String getC3() {
		return this.c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	@Column(name = "c4", length = 64)
	public String getC4() {
		return this.c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	@Column(name = "c5", length = 64)
	public String getC5() {
		return this.c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	@Column(name = "c6", length = 64)
	public String getC6() {
		return this.c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	@Column(name = "c7", length = 64)
	public String getC7() {
		return this.c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	@Column(name = "c8", length = 64)
	public String getC8() {
		return this.c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	@Column(name = "c9", length = 64)
	public String getC9() {
		return this.c9;
	}

	public void setC9(String c9) {
		this.c9 = c9;
	}

	@Column(name = "c10", length = 64)
	public String getC10() {
		return this.c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
	}

	@Column(name = "c11", length = 64)
	public String getC11() {
		return this.c11;
	}

	public void setC11(String c11) {
		this.c11 = c11;
	}

	@Column(name = "c12", length = 64)
	public String getC12() {
		return this.c12;
	}

	public void setC12(String c12) {
		this.c12 = c12;
	}

	@Column(name = "c13", length = 64)
	public String getC13() {
		return this.c13;
	}

	public void setC13(String c13) {
		this.c13 = c13;
	}

	@Column(name = "c14", length = 64)
	public String getC14() {
		return this.c14;
	}

	public void setC14(String c14) {
		this.c14 = c14;
	}

	@Column(name = "c15", length = 64)
	public String getC15() {
		return this.c15;
	}

	public void setC15(String c15) {
		this.c15 = c15;
	}

	@Column(name = "c16", length = 64)
	public String getC16() {
		return this.c16;
	}

	public void setC16(String c16) {
		this.c16 = c16;
	}

	@Column(name = "c17", length = 64)
	public String getC17() {
		return this.c17;
	}

	public void setC17(String c17) {
		this.c17 = c17;
	}

	@Column(name = "c18", length = 64)
	public String getC18() {
		return this.c18;
	}

	public void setC18(String c18) {
		this.c18 = c18;
	}

	@Column(name = "c19", length = 64)
	public String getC19() {
		return this.c19;
	}

	public void setC19(String c19) {
		this.c19 = c19;
	}

	@Column(name = "c20", length = 64)
	public String getC20() {
		return this.c20;
	}

	public void setC20(String c20) {
		this.c20 = c20;
	}

	@Column(name = "c21", length = 64)
	public String getC21() {
		return this.c21;
	}

	public void setC21(String c21) {
		this.c21 = c21;
	}

	@Column(name = "c22", length = 64)
	public String getC22() {
		return this.c22;
	}

	public void setC22(String c22) {
		this.c22 = c22;
	}

	@Column(name = "c23", length = 64)
	public String getC23() {
		return this.c23;
	}

	public void setC23(String c23) {
		this.c23 = c23;
	}

	@Column(name = "c24", length = 64)
	public String getC24() {
		return this.c24;
	}

	public void setC24(String c24) {
		this.c24 = c24;
	}

	@Column(name = "c25", length = 64)
	public String getC25() {
		return this.c25;
	}

	public void setC25(String c25) {
		this.c25 = c25;
	}

	@Column(name = "c26", length = 64)
	public String getC26() {
		return this.c26;
	}

	public void setC26(String c26) {
		this.c26 = c26;
	}

	@Column(name = "c27", length = 64)
	public String getC27() {
		return this.c27;
	}

	public void setC27(String c27) {
		this.c27 = c27;
	}

	@Column(name = "c28", length = 64)
	public String getC28() {
		return this.c28;
	}

	public void setC28(String c28) {
		this.c28 = c28;
	}

	@Column(name = "c29", length = 64)
	public String getC29() {
		return this.c29;
	}

	public void setC29(String c29) {
		this.c29 = c29;
	}

	@Column(name = "c30", length = 64)
	public String getC30() {
		return this.c30;
	}

	public void setC30(String c30) {
		this.c30 = c30;
	}

	@Column(name = "c31", length = 64)
	public String getC31() {
		return this.c31;
	}

	public void setC31(String c31) {
		this.c31 = c31;
	}

	@Column(name = "c32", length = 64)
	public String getC32() {
		return this.c32;
	}

	public void setC32(String c32) {
		this.c32 = c32;
	}

	@Column(name = "c33", length = 64)
	public String getC33() {
		return this.c33;
	}

	public void setC33(String c33) {
		this.c33 = c33;
	}

	@Column(name = "c34", length = 64)
	public String getC34() {
		return this.c34;
	}

	public void setC34(String c34) {
		this.c34 = c34;
	}

	@Column(name = "c35", length = 64)
	public String getC35() {
		return this.c35;
	}

	public void setC35(String c35) {
		this.c35 = c35;
	}

	@Column(name = "c36", length = 64)
	public String getC36() {
		return this.c36;
	}

	public void setC36(String c36) {
		this.c36 = c36;
	}

	@Column(name = "c37", length = 64)
	public String getC37() {
		return this.c37;
	}

	public void setC37(String c37) {
		this.c37 = c37;
	}

	@Column(name = "c38", length = 64)
	public String getC38() {
		return this.c38;
	}

	public void setC38(String c38) {
		this.c38 = c38;
	}

	@Column(name = "c39", length = 64)
	public String getC39() {
		return this.c39;
	}

	public void setC39(String c39) {
		this.c39 = c39;
	}

	@Column(name = "c40", length = 64)
	public String getC40() {
		return this.c40;
	}

	public void setC40(String c40) {
		this.c40 = c40;
	}

	@Column(name = "c41", length = 64)
	public String getC41() {
		return this.c41;
	}

	public void setC41(String c41) {
		this.c41 = c41;
	}

	@Column(name = "c42", length = 64)
	public String getC42() {
		return this.c42;
	}

	public void setC42(String c42) {
		this.c42 = c42;
	}

	@Column(name = "c43", length = 64)
	public String getC43() {
		return this.c43;
	}

	public void setC43(String c43) {
		this.c43 = c43;
	}

	@Column(name = "c44", length = 64)
	public String getC44() {
		return this.c44;
	}

	public void setC44(String c44) {
		this.c44 = c44;
	}

	@Column(name = "c45", length = 64)
	public String getC45() {
		return this.c45;
	}

	public void setC45(String c45) {
		this.c45 = c45;
	}

	@Column(name = "c46", length = 64)
	public String getC46() {
		return this.c46;
	}

	public void setC46(String c46) {
		this.c46 = c46;
	}

	@Column(name = "c47", length = 64)
	public String getC47() {
		return this.c47;
	}

	public void setC47(String c47) {
		this.c47 = c47;
	}

	@Column(name = "c48", length = 64)
	public String getC48() {
		return this.c48;
	}

	public void setC48(String c48) {
		this.c48 = c48;
	}

	@Column(name = "c49", length = 64)
	public String getC49() {
		return this.c49;
	}

	public void setC49(String c49) {
		this.c49 = c49;
	}

	@Column(name = "c50", length = 64)
	public String getC50() {
		return this.c50;
	}

	public void setC50(String c50) {
		this.c50 = c50;
	}

	@Column(name = "c51", length = 64)
	public String getC51() {
		return this.c51;
	}

	public void setC51(String c51) {
		this.c51 = c51;
	}

	@Column(name = "c52", length = 64)
	public String getC52() {
		return this.c52;
	}

	public void setC52(String c52) {
		this.c52 = c52;
	}

	@Column(name = "c53", length = 64)
	public String getC53() {
		return this.c53;
	}

	public void setC53(String c53) {
		this.c53 = c53;
	}

	@Column(name = "c54", length = 64)
	public String getC54() {
		return this.c54;
	}

	public void setC54(String c54) {
		this.c54 = c54;
	}

	@Column(name = "c55", length = 64)
	public String getC55() {
		return this.c55;
	}

	public void setC55(String c55) {
		this.c55 = c55;
	}

	@Column(name = "c56", length = 64)
	public String getC56() {
		return this.c56;
	}

	public void setC56(String c56) {
		this.c56 = c56;
	}

	@Column(name = "c57", length = 64)
	public String getC57() {
		return this.c57;
	}

	public void setC57(String c57) {
		this.c57 = c57;
	}

	@Column(name = "c58", length = 64)
	public String getC58() {
		return this.c58;
	}

	public void setC58(String c58) {
		this.c58 = c58;
	}

	@Column(name = "c59", length = 64)
	public String getC59() {
		return this.c59;
	}

	public void setC59(String c59) {
		this.c59 = c59;
	}

	@Column(name = "c60", length = 64)
	public String getC60() {
		return this.c60;
	}

	public void setC60(String c60) {
		this.c60 = c60;
	}

	@Column(name = "c61", length = 64)
	public String getC61() {
		return this.c61;
	}

	public void setC61(String c61) {
		this.c61 = c61;
	}

	@Column(name = "c62", length = 64)
	public String getC62() {
		return this.c62;
	}

	public void setC62(String c62) {
		this.c62 = c62;
	}

	@Column(name = "c63", length = 64)
	public String getC63() {
		return this.c63;
	}

	public void setC63(String c63) {
		this.c63 = c63;
	}

	@Column(name = "c64", length = 64)
	public String getC64() {
		return this.c64;
	}

	public void setC64(String c64) {
		this.c64 = c64;
	}
	@Column(name = "p1", length = 128)
	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}
	@Column(name = "p2", length = 128)
	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}
	@Column(name = "p3", length = 128)
	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}
	@Column(name = "p4", length = 128)
	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}
	@Column(name = "p5", length = 128)
	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}
	@Column(name = "p6", length = 128)
	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}
	@Column(name = "p7", length = 128)
	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}
	@Column(name = "p8", length = 128)
	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}
	@Column(name = "p9", length = 128)
	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}
	@Column(name = "p10", length = 128)
	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}
	@Column(name = "p11", length = 128)
	public String getP11() {
		return p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}
	@Column(name = "p12", length = 128)
	public String getP12() {
		return p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}
	@Column(name = "intent", length = 64)
	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}
	@Column(name = "type", length = 64)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "kind", length = 64)
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	@Column(name = "how", length = 64)
	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}
	@Column(name = "inn", length = 64)
	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}
	@Column(name = "inkind", length = 64)
	public String getInkind() {
		return inkind;
	}

	public void setInkind(String inkind) {
		this.inkind = inkind;
	}
	@Column(name = "item", length = 64)
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	@Column(name = "item1", length = 64)
	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}
	@Column(name = "ni", length = 64)
	public String getNi() {
		return ni;
	}

	public void setNi(String ni) {
		this.ni = ni;
	}
	@Column(name = "tele", length = 64)
	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
}
