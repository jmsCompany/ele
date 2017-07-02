package qingyun.ele.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.*;
import qingyun.ele.repository.CodeNumRepository;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.InfoRepository;
import qingyun.ele.repository.LoanRepository;
import qingyun.ele.repository.ProjectStepsRepository;
import qingyun.ele.repository.StepsRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.repository.TransferSheetRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.EmailSenderService;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.*;


@RestController
@Transactional(readOnly = true)
public class CustomerController {

	@Autowired
	private UsrService usrService;
	@Autowired
	private SubSubLocationRepository subSubLocationRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DicRepository dicRepository;
	@Autowired
	private StepsRepository stepsRepository;
	@Autowired
	private ProjectStepsRepository projectStepsRepository;
	@Autowired
	private SecurityUtils securityUtils;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private InfoRepository infoRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private TransferSheetRepository transferSheetRepository;
	@Autowired
	private CodeNumRepository codeNumRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	private static final Log logger = LogFactory.getLog(CustomerController.class);

	// 界面中需要添加项目开始时间，项目结束时间。
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveProject", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveProject(@RequestBody Customer customer) {
		Valid v = new Valid();
		Customer dbCustomer;
		// create new
		if (customer.getId() == null || customer.getId().equals(0l)) {
			dbCustomer = new Customer();
			dbCustomer.setDeleted(0l);
			dbCustomer.setCreationTime(new Date());
			dbCustomer.setStart(new Date());
			dbCustomer.setCreator(securityUtils.getCurrentDBUser().getId());
			SubSubLocation subSubLocation = subSubLocationRepository.findOne(customer.getSubSubLocation().getId());
			CodeNum codeNum = codeNumRepository.findByPrefixAndDesc(subSubLocation.getCode(),"project");
			if (codeNum==null){
				//新增一条数据
				codeNum=new CodeNum();
				codeNum.setDescr("project");
				codeNum.setCurr_val(0l);
				codeNum.setPrefix(subSubLocation.getCode());
				codeNumRepository.save(codeNum);
			}
			Long currentVal = codeNum.getCurr_val();
			String code = codeNum.getPrefix() + String.format("%04d", currentVal);
			dbCustomer.setCode(code);
			dbCustomer.setDic(new Dic(7l));
			codeNum.setCurr_val(currentVal + 1);
			codeNumRepository.save(codeNum);
		} else {
			dbCustomer = customerRepository.findOne(customer.getId());

		}
		// dbCustomer.setCode(customer.getCode());
		dbCustomer.setName(customer.getName());
		dbCustomer.setProject(customer.getProject());
		dbCustomer.setAddress(customer.getAddress());
		if (customer.getStart()!=null){
			dbCustomer.setStart(customer.getStart());
		}
		dbCustomer.setEnd(customer.getEnd());
		dbCustomer.setProcess(customer.getProcess());
		dbCustomer.setSaleMan(customer.getSaleMan());
		dbCustomer.setCurrStep(customer.getCurrStep());
		dbCustomer.setCommit(customer.getCommit());
		dbCustomer.setContent(customer.getContent());
		dbCustomer.setLat(customer.getLat());
		dbCustomer.setLng(customer.getLng());
		dbCustomer.setMobile(customer.getMobile());
		//新增功能 保存 C1到C64 字段
		dbCustomer.setC1(customer.getC1());
		dbCustomer.setC2(customer.getC2());
		dbCustomer.setC3(customer.getC3());
		dbCustomer.setC4(customer.getC4());
		dbCustomer.setC5(customer.getC5());
		dbCustomer.setC6(customer.getC6());
		dbCustomer.setC7(customer.getC7());
		dbCustomer.setC8(customer.getC8());
		dbCustomer.setC9(customer.getC9());
		dbCustomer.setC10(customer.getC10());
		dbCustomer.setC11(customer.getC11());
		dbCustomer.setC12(customer.getC12());
		dbCustomer.setC13(customer.getC13());
		dbCustomer.setC14(customer.getC14());
		dbCustomer.setC15(customer.getC15());
		dbCustomer.setC16(customer.getC16());
		dbCustomer.setC17(customer.getC17());
		dbCustomer.setC18(customer.getC18());
		dbCustomer.setC19(customer.getC19());
		dbCustomer.setC20(customer.getC20());
		dbCustomer.setC21(customer.getC21());
		dbCustomer.setC22(customer.getC22());
		dbCustomer.setC23(customer.getC23());
		dbCustomer.setC24(customer.getC24());
		dbCustomer.setC25(customer.getC25());
		dbCustomer.setC26(customer.getC26());
		dbCustomer.setC27(customer.getC27());
		dbCustomer.setC28(customer.getC28());
		dbCustomer.setC29(customer.getC29());
		dbCustomer.setC30(customer.getC30());
		dbCustomer.setC31(customer.getC31());
		dbCustomer.setC32(customer.getC32());
		dbCustomer.setC33(customer.getC33());
		dbCustomer.setC34(customer.getC34());
		dbCustomer.setC35(customer.getC35());
		dbCustomer.setC36(customer.getC36());
		dbCustomer.setC37(customer.getC37());
		dbCustomer.setC38(customer.getC38());
		dbCustomer.setC39(customer.getC39());
		dbCustomer.setC40(customer.getC40());
		dbCustomer.setC41(customer.getC41());
		dbCustomer.setC42(customer.getC42());
		dbCustomer.setC43(customer.getC43());
		dbCustomer.setC44(customer.getC44());
		dbCustomer.setC45(customer.getC45());
		dbCustomer.setC46(customer.getC46());
		dbCustomer.setC47(customer.getC47());
		dbCustomer.setC48(customer.getC48());
		dbCustomer.setC49(customer.getC49());
		dbCustomer.setC50(customer.getC50());
		dbCustomer.setC51(customer.getC51());
		dbCustomer.setC52(customer.getC52());
		dbCustomer.setC53(customer.getC53());
		dbCustomer.setC54(customer.getC54());
		dbCustomer.setC55(customer.getC55());
		dbCustomer.setC56(customer.getC56());
		dbCustomer.setC57(customer.getC57());
		dbCustomer.setC58(customer.getC58());
		dbCustomer.setC59(customer.getC59());
		dbCustomer.setC60(customer.getC60());
		dbCustomer.setC61(customer.getC61());
		dbCustomer.setC62(customer.getC62());
		dbCustomer.setC63(customer.getC63());
		dbCustomer.setC64(customer.getC64());


		//新增功能 保存 P1到P12 字段
		dbCustomer.setP1(customer.getP1());
		dbCustomer.setP2(customer.getP2());
		dbCustomer.setP3(customer.getP3());
		dbCustomer.setP4(customer.getP4());
		dbCustomer.setP5(customer.getP5());
		dbCustomer.setP6(customer.getP6());
		dbCustomer.setP7(customer.getP7());
		dbCustomer.setP8(customer.getP8());
		dbCustomer.setP9(customer.getP9());
		dbCustomer.setP10(customer.getP10());
		dbCustomer.setP11(customer.getP11());
		dbCustomer.setP12(customer.getP12());


//		Long idDic = customer.getDic().getId();
//		if (idDic != null) {
//			dbCustomer.setDic(dicRepository.findOne(idDic));
//		}
		Long idSubSubLocation = customer.getSubSubLocation().getId();
		if (idSubSubLocation != null) {
			dbCustomer.setSubSubLocation(subSubLocationRepository.findOne(idSubSubLocation));
		}
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}

	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveSoTrack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSoTrack(@RequestBody Customer customer) {
		Valid v = new Valid();
		Customer dbCustomer = customerRepository.findOne(customer.getId());

		dbCustomer.setActVol(customer.getActVol());
		dbCustomer.setAgentCost(customer.getAgentCost());
		dbCustomer.setDevCost(customer.getDevCost());
		dbCustomer.setDurationLoan(customer.getDurationLoan());
		dbCustomer.setLoanTime(customer.getLoanTime());
		dbCustomer.setManagementCost(customer.getManagementCost());
		dbCustomer.setMonthIncome(customer.getMonthIncome());
		dbCustomer.setMonthLoan(customer.getMonthLoan());
		dbCustomer.setNetProfit(customer.getNetProfit());
		dbCustomer.setSaleCost(customer.getSaleCost());
		dbCustomer.setUnitCost(customer.getUnitCost());
		dbCustomer.setUnitPrice(customer.getUnitPrice());
		
		float mainternanceCost = 0f;
		if (customer.getMainternanceCost() != null) {
			mainternanceCost = customer.getMainternanceCost();
		}
		dbCustomer.setMainternanceCost(mainternanceCost);
		
		if (dbCustomer.getSoCreationTime() == null) {
			dbCustomer.setSoCreationTime(new Date());
		}

		float agent = 0f;
		if (customer.getAgentCost() != null) {
			agent = customer.getAgentCost();
		}

		float saleCost = 0f;
		if (customer.getSaleCost() != null) {
			saleCost = customer.getSaleCost();
		}

		float manage = 0f;
		if (customer.getManagementCost() != null) {
			manage = customer.getManagementCost();
		}

		Float devCost = agent + saleCost + manage +mainternanceCost;
		dbCustomer.setDevCost(devCost);

		Float actVol = 0f;
		if (customer.getActVol() != null) {
			actVol = customer.getActVol();
		}
		Float unitPrice = 0f;
		if (customer.getUnitPrice() != null) {
			unitPrice = customer.getUnitPrice();
		}

		Float unitCost = 0f;
		if (customer.getUnitCost() != null) {
			unitCost = customer.getUnitCost();
		}
		long dur = 0l;
		if (customer.getDurationLoan() != null) {
			dur = customer.getDurationLoan();
		}
		Float monIncome = 0f;
		if (customer.getMonthIncome() != null) {
			monIncome = customer.getMonthIncome();

		}
		Float monLoan = 0f;
		if (customer.getMonthLoan() != null) {
			monLoan = customer.getMonthLoan();

		}
		

		
		float precent = 0f;
		if (customer.getPercent()!= null) {
			precent = customer.getPercent();
		}
		dbCustomer.setMainternanceCost(mainternanceCost);
		dbCustomer.setPercent(precent);
		
		// 净利=实际容量*（销售价格-建设成本）-开发费用总额+（贷款期限*（预计每月售电收入-每月还贷金额））
		Float netProfit = actVol * (unitPrice - unitCost) - devCost + (dur * (monIncome - monLoan));
		dbCustomer.setNetProfit(netProfit);
		
//		// 实利=净利*比例
//		if(netProfit!=null&&customer.getPercent()!=null)
//		{
//			dbCustomer.setActProfit(netProfit*customer.getPercent());
//		}
		
		
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/project/getSoTrack", method = RequestMethod.GET)
	public WSSoTrack getSoTrack(@RequestParam Long projectId) {
		// Valid v = new Valid();
		WSSoTrack dbCustomer = new WSSoTrack();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Customer customer = customerRepository.findOne(projectId);
		if (customer != null) {
			dbCustomer.setId(customer.getId());
			dbCustomer.setCode(customer.getCode());
			dbCustomer.setAddress(customer.getSubSubLocation().getName());
			if (customer.getSaleMan() != null) {
				dbCustomer.setSalesMan(usersRepository.findOne(customer.getSaleMan()).getName());
			} else {
				dbCustomer.setSalesMan("");
			}
			TransferSheet transferSheet = transferSheetRepository.findByIdProject(projectId);
			//从form2中取实际容量 actVol
			dbCustomer.setActVol(transferSheet.getActVol());
			dbCustomer.setAgentCost(customer.getAgentCost());
			dbCustomer.setUnitCost(customer.getUnitCost());
			// dbCustomer.setDevCost(customer.getDevCost());
			dbCustomer.setDurationLoan(customer.getDurationLoan());
			dbCustomer.setLoanTime(customer.getLoanTime());
			dbCustomer.setManagementCost(customer.getManagementCost());
			dbCustomer.setMonthIncome(customer.getMonthIncome());
			dbCustomer.setMonthLoan(customer.getMonthLoan());

			dbCustomer.setSaleCost(customer.getSaleCost());
			dbCustomer.setUnitCost(customer.getUnitCost());
			dbCustomer.setUnitPrice(customer.getUnitPrice());
			dbCustomer.setMainternanceCost(customer.getMainternanceCost());
			dbCustomer.setPercent(customer.getPercent());

			//从loan表中取出：每月还款时间,还款金额,贷款年限,预计每月收入
			Loan loan = loanRepository.findByIdProject(projectId);
			dbCustomer.setPaymentTime(loan.getPaymentTime());
			dbCustomer.setAmountPermonth(loan.getAmountPermonth());
			dbCustomer.setDuration(loan.getDuration());
			dbCustomer.setEstimateIncomePermonth(loan.getEstimateIncomePermonth());
		
			if (customer.getSoCreationTime() != null) {
				dbCustomer.setSoCreationTime(formatter.format(customer.getSoCreationTime()));
			} else {
				dbCustomer.setSoCreationTime("");
			}

			float agent = 0f;
			if (customer.getAgentCost() != null) {
				agent = customer.getAgentCost();
			}

			float saleCost = 0f;
			if (customer.getSaleCost() != null) {
				saleCost = customer.getSaleCost();
			}

			float manage = 0f;
			if (customer.getManagementCost() != null) {
				manage = customer.getManagementCost();
			}
			
			float mainternanceCost = 0f;
			if (customer.getMainternanceCost() != null) {
				mainternanceCost = customer.getMainternanceCost();
			}

			Float devCost = agent + saleCost + manage + mainternanceCost;
			dbCustomer.setDevCost(devCost);

			Float actVol = 0f;
			if (customer.getActVol() != null) {
				actVol = customer.getActVol();
			}
			Float unitPrice = 0f;
			if (customer.getUnitPrice() != null) {
				unitPrice = customer.getUnitPrice();
			}

			Float unitCost = 0f;
			if (customer.getUnitCost() != null) {
				unitCost = customer.getUnitCost();
			}
			long dur = 0l;
			if (customer.getDurationLoan() != null) {
				dur = customer.getDurationLoan();
			}
			Float monIncome = 0f;
			if (customer.getMonthIncome() != null) {
				monIncome = customer.getMonthIncome();

			}
			Float monLoan = 0f;
			if (customer.getMonthLoan() != null) {
				monLoan = customer.getMonthLoan();

			}
			// 净利=实际容量*（销售价格-建设成本）-开发费用总额+（贷款期限*（预计每月售电收入-每月还贷金额））

			Float netProfit = actVol * (unitPrice - unitCost) - devCost + (dur * (monIncome - monLoan));
			dbCustomer.setNetProfit(netProfit);
			// 实利=净利*比例
			if(netProfit!=null&&customer.getPercent()!=null)
			{
				dbCustomer.setActProfit(netProfit*customer.getPercent());
			}
			
			dbCustomer.setName(customer.getName());
		}

		return dbCustomer;
	}

	/**
	 * 分页查询项目列表
	 * @param q 查询关键字
	 * @param start 开始页
	 * @param draw
	 * @param length 页面长度
     * @return
     */
	@RequestMapping(value = "/project/projectTable", method = RequestMethod.POST)
	public WSTableData projectTable(@RequestParam(required = false, value = "q") String q,@RequestParam Integer start, @RequestParam Integer draw,
			@RequestParam Integer length,@RequestParam Long status) {
		//获取当前登录用户的对象
		Users sessionUser = securityUtils.getCurrentDBUser();
		//计算当前页码
		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		System.out.println("user id: " +sessionUser.getId());
		Page<Customer> customers;
		//判断是否输入查询关键字,如果有则按照关键字查询,否则根据登录用户的角色查询
		if (q == null||"".equals(q)) {
			customers = customerRepository.findAllCustomersByRoleIdAndStatus(sessionUser.getId(),status, pageable);
		} else {
			customers = customerRepository.findByQAndStatus(q, sessionUser.getId(),status, pageable);
		}
		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {
			SubSubLocation s = w.getSubSubLocation();
			String loc = s.getSubLocation().getLocation().getName() + "," + s.getSubLocation().getName() + ","
					+ s.getName();
			String saleMan = "";
			if (w.getSaleMan() != null) {
				try{
					saleMan = usersRepository.findOne(w.getSaleMan()).getName();
				}catch (Exception e){
					saleMan="";
				}

			}
			String p = "";
			if(w.getDic()!=null)
			{
				p =""+ w.getDic().getId();
			}
			//获得工程ID
			Long id_project=w.getId();
			//根据工程ID获得施工单状态和贷款单状态。
			//施工单状态
			String transfer_Flag="0";
			if(transferSheetRepository.findByIdProject(id_project)!=null){
				transfer_Flag="1";
			};
			//贷款单状态
			String loan_Flag="0";
			if(loanRepository.findByIdProject(id_project)!=null){
				loan_Flag="1";
			};

			String[] d = { "" + w.getId(), w.getCode(), w.getName(), w.getAddress(), w.getProject(), loc,w.getDic().getCode(),
					 saleMan, "" + w.getId(),"" + w.getId(),"" + transfer_Flag,"" + loan_Flag, "" + p, "" + w.getId() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	// 删除项目，其实是修改项目Deleted
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/deleteProject", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid deleteProject(@RequestParam("idProject") Long idProject) {
		Valid v = new Valid();
		Customer dbCustomer = customerRepository.findOne(idProject);
		dbCustomer.setDeleted(1l);
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}

	
	@RequestMapping(value = "/project/projectSelects", method = RequestMethod.GET)
	public List<WSSelectObj> projectSelects() {

		Users sessionUser = securityUtils.getCurrentDBUser();
		List<WSSelectObj> ws = new ArrayList<WSSelectObj>();
		WSSelectObj w1 = new WSSelectObj("", "请选择项目");
		ws.add(w1);
		for (Customer d : customerRepository.findAllCustomers(sessionUser.getId())) {
			WSSelectObj w = new WSSelectObj(""+d.getId(), d.getProject());
			ws.add(w);
		}
		return ws;
	}

	
	
	@RequestMapping(value = "/project/projectStepsTable", method = RequestMethod.POST)
	public WSTableData projectStepsTable(@RequestParam Long projectId, @RequestParam Integer start,
			@RequestParam Integer draw, @RequestParam Integer length) {

		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<Steps> steps = stepsRepository.findAll(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		for (Steps w : steps.getContent()) {
			// logger.debug("step:" + w.getName());
			String act_start = "";
			String act_end = "";
			String act_days = "";
			String delay = "";
			String status = "";
			String remark = "";
			String depart = "";
			String emp = "";
			String form = w.getForm();
			ProjectSteps projectSteps = projectStepsRepository.findByProjectIdAndStepId(projectId, w.getId());
			Long id = 0l;
			if (projectSteps != null) {
				id = projectSteps.getId();
				if (projectSteps.getStart() != null) {
					act_start = formatter.format(projectSteps.getStart());
				}
				if (projectSteps.getEnd() != null) {
					act_end = formatter.format(projectSteps.getEnd());
				}

				act_days = "";
				if (projectSteps.getActDays() != null) {
					act_days = "" + projectSteps.getActDays();
				}

				if (projectSteps.getDicByStatus() != null) {
					status = projectSteps.getDicByStatus().getCode();
				}

				remark = projectSteps.getRemark();
				// 11正常，12延迟13结束

				if (projectSteps.getEnd() != null) {
					delay = "结束";
				} else {
					if (projectSteps.getStart() != null) {

						long lastedDays = projectSteps.getSteps().getLastedDays(); // 最慢天数设置
						Date today = new Date();
						Date startDay = projectSteps.getStart();
						// 已经持续天数
						long days = (today.getTime() - startDay.getTime()) / (1000 * 3600 * 24);

						long delayDays = days - lastedDays;
						// logger.debug("step:" + w.getName()+", delay days: " +
						// delayDays);
						if (delayDays > 0) {
							delay = "延迟";
						} else {
							delay = "正常";
						}
					}
				}
				// if (projectSteps.getDicByProgress() != null) {
				// delay = projectSteps.getDicByProgress().getCode();
				// }
				//
				if (projectSteps.getUsers() != null) {
					emp = projectSteps.getUsers().getName();
					if (projectSteps.getUsers().getDicByDepartment() != null) {
						depart = projectSteps.getUsers().getDicByDepartment().getCode();
					}

				}
			}

			String[] d = { "" + id, "" + w.getId(), w.getName(), "" + w.getForcastDays(), "" + w.getLastedDays(),
					act_start, act_end, act_days, delay, status, remark, depart, emp, form, "" + id };
			lst.add(d);
			// logger.debug("id:" + id);
		}
		// logger.debug("size: " + lst.size());
		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) steps.getTotalElements());
		t.setRecordsFiltered((int) steps.getTotalElements());
		t.setData(lst);
		return t;
	}

	
	// 实际开始，实际结束，进度。状态，备注
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveProjectSteps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveProjectSteps(@RequestBody WSProjectSteps wsProjectSteps) {
		Valid v = new Valid();

		Steps step = stepsRepository.findOne(wsProjectSteps.getStepId());
		ProjectSteps dbProjectSteps;
		// create new
		if (wsProjectSteps.getId() == null || wsProjectSteps.getId().equals(0l)) {
			dbProjectSteps = new ProjectSteps();
		} else {
			dbProjectSteps = projectStepsRepository.findOne(wsProjectSteps.getId());
		}
		Customer c = customerRepository.findOne(wsProjectSteps.getCustomerId());

		if (c != null) {
			if (c.getCurrentStep() != null) {
				if (wsProjectSteps.getStepId() != null) {
					if (wsProjectSteps.getStepId() > wsProjectSteps.getStepId()) {
						c.setCurrentStep(wsProjectSteps.getStepId());
					}
				}
			} else {
				c.setCurrentStep(wsProjectSteps.getStepId());
			}
			customerRepository.save(c);
		}
		dbProjectSteps.setCustomer(c);
		if (wsProjectSteps.getDepartmentId() != null) {
			dbProjectSteps.setDicByDepartment(dicRepository.findOne(wsProjectSteps.getDepartmentId()));
		}
		// 11正常，12延迟13结束
		if (wsProjectSteps.getIdProgress() != null) {
			dbProjectSteps.setDicByProgress(dicRepository.findOne(wsProjectSteps.getIdProgress()));
		}

		dbProjectSteps.setForcastDays(wsProjectSteps.getForcastDays());
		dbProjectSteps.setLastedDays(wsProjectSteps.getLastedDays());
		dbProjectSteps.setName(wsProjectSteps.getName());
		dbProjectSteps.setRemark(wsProjectSteps.getRemark());

		if (wsProjectSteps.getStart() != null && wsProjectSteps.getEnd() != null) {
			long days = (wsProjectSteps.getEnd().getTime() - wsProjectSteps.getStart().getTime()) / (1000 * 3600 * 24);
			dbProjectSteps.setActDays(days);
		}
		if (wsProjectSteps.getStepId() != null) {
			dbProjectSteps.setSteps(stepsRepository.findOne(wsProjectSteps.getStepId()));
		}
		if (wsProjectSteps.getUserId() != null) {
			dbProjectSteps.setUsers(usersRepository.findOne(wsProjectSteps.getUserId()));
		}

		if (wsProjectSteps.getStatusId() != null) {
			dbProjectSteps.setDicByStatus(dicRepository.findOne(wsProjectSteps.getStatusId()));
		}
		
		if(wsProjectSteps.getStart() != null && wsProjectSteps.getEnd() != null)
		{
			if(wsProjectSteps.getEnd().before(wsProjectSteps.getStart()))
			{
				v.setValid(false);
				v.setMsg("项目实际结束时间不能早于项目实际开始时间");
				return v;
			}
		}
		
		if (wsProjectSteps.getStart() != null) {
			if (dbProjectSteps.getStart() == null) {
				// send email
				if (step.getStart_email() != null) {
					sendEmail("项目步骤开始", step.getStart_email(), dbProjectSteps);
				}
			}
			dbProjectSteps.setStart(wsProjectSteps.getStart());
		}
		if (wsProjectSteps.getEnd() != null) {

			if (dbProjectSteps.getEnd() == null) {
				// send email
				if (step.getEnd_email() != null) {
					sendEmail("项目步骤结束", step.getEnd_email(), dbProjectSteps);
				}
			}
			dbProjectSteps.setEnd(wsProjectSteps.getEnd());
		}
		

		projectStepsRepository.save(dbProjectSteps);
		v.setValid(true);
		return v;
	}

	private void sendEmail(String subject, String email, ProjectSteps ps) {
		String[] to = new String[] { email };
		String content = "";
		content = "客户: " + ps.getCustomer().getName() + ",项目: " + ps.getCustomer().getProject() + "\r\n";
		content = content + "项目步骤: " + ps.getSteps().getName();
		try {
			emailSenderService.sendEmail(to, subject, content, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@Transactional(readOnly = false)
//	@RequestMapping(value = "/project/saveForm1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public Valid saveForm1(@RequestBody Info info) {
//		Valid v = new Valid();
//		Long projectId = info.getIdProject();
//		Info dbInfo = infoRepository.findByIdProject(projectId);
//		if (dbInfo != null) {
//			info.setId(dbInfo.getId());
//			info.setCode(dbInfo.getCode());
//		} else {
//			CodeNum codeNum = codeNumRepository.findByIdforUpdate(3l);
//			Long currentVal = codeNum.getCurr_val();
//			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
//			info.setCode(code);
//			codeNum.setCurr_val(currentVal + 1);
//			codeNumRepository.save(codeNum);
//		}
//
//		infoRepository.save(info);
//		v.setValid(true);
//		return v;
//	}

	
	//信息采集表
	//项目基本信息从项目中抓取
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getForm1", method = RequestMethod.GET)
	public Object getForm1(@RequestParam Long projectId) {

		Customer c = customerRepository.findOne(projectId);
		if (c == null) {
			c = new Customer();
		}
		Map<String,Object> map=new HashMap<>();
		map.put("id",c!=null?c.getId():0);
		map.put("code",c!=null?c.getCode():"");
		map.put("content","");

		//得到类对象
		Class customerCla = (Class) c.getClass();
		//得到该类下所有的属性
/*
        * 得到类中的所有属性集合
        */
		Map<String,Object> mapNew=new HashMap<>();
		Field[] fs = customerCla.getDeclaredFields();
		for(int i = 0 ; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); //设置些属性是可以访问的
			Object val ="";
			try {
				val = f.get(c).toString();//得到此属性的值
			}catch (Exception e){
				val="";
			}
			//System.out.println("name2:" + f.getName() + "\t value2 = " + val);
			mapNew.put(f.getName(),val);

		}
		map.put("content",mapNew);


		//Info dbInfo = infoRepository.findByIdProject(projectId);



	//	map.put("content",c!=null?c.getContent():"");
		return map;
	}

	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveForm2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveForm2(@RequestBody TransferSheet transferSheet) {
		Valid v = new Valid();

		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(transferSheet.getIdProject());
		if (dbTransferSheet != null) {
			transferSheet.setId(dbTransferSheet.getId());
			transferSheet.setCode(dbTransferSheet.getCode());
		}
//		else {
//			CodeNum codeNum = codeNumRepository.findByIdforUpdate(4l);
//			Long currentVal = codeNum.getCurr_val();
//			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
//			transferSheet.setCode(code);
//			codeNum.setCurr_val(currentVal + 1);
//			codeNumRepository.save(codeNum);
//		}

		transferSheetRepository.save(transferSheet);
		v.setValid(true);
		return v;
	}

	
	//施工-验收-贷款流转单
	//项目基本信息从项目中抓取
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getForm2", method = RequestMethod.GET)
	public TransferSheet getForm2(@RequestParam Long projectId) {

		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(projectId);
		if (dbTransferSheet == null) {
			dbTransferSheet = new TransferSheet();
		}
		Customer c = customerRepository.findOne(projectId);
		dbTransferSheet.setProjectName(c.getProject());
		dbTransferSheet.setAddress(c.getAddress());
		return dbTransferSheet;

	}

	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveForm3", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveForm3(@RequestBody Loan loan) {
		Valid v = new Valid();
		Long projectId = loan.getIdProject();
		Loan dbLoan = loanRepository.findByIdProject(projectId);
		if (dbLoan != null) {
			loan.setId(dbLoan.getId());
			loan.setCode(dbLoan.getCode());
		}

//		else {
//			CodeNum codeNum = codeNumRepository.findByIdforUpdate(5l);
//			Long currentVal = codeNum.getCurr_val();
//			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
//			loan.setCode(code);
//			codeNum.setCurr_val(currentVal + 1);
//			codeNumRepository.save(codeNum);
//		}

		loanRepository.save(loan);
		v.setValid(true);
		return v;
	}
	

	
	//贷款根据单
	//项目基本信息从项目中抓取
	//财务数据从销售跟踪单上抓取
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getForm3", method = RequestMethod.GET)
	public Loan getForm3(@RequestParam Long projectId) {

		Loan dbLoan = loanRepository.findByIdProject(projectId);
		if (dbLoan == null) {
			dbLoan = new Loan();
		}
		Customer c = customerRepository.findOne(projectId);
		dbLoan.setC1(c.getAddress());
		dbLoan.setC2(c.getProject());
		dbLoan.setC3(c.getName());

		dbLoan.setDuration(c.getDurationLoan()/12);
		dbLoan.setAmountPermonth(c.getMonthLoan());
		dbLoan.setPaymentTime(c.getLoanTime());
		dbLoan.setIdProject(c.getId());
		// private Long capacity;//实际容量 form2

		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(projectId);
		if (dbTransferSheet != null) {
			dbLoan.setCapacity(dbTransferSheet.getActVol()+"");
		}

		return dbLoan;

	}


	/**
	 * 获取项目步骤状态
	 * @param projId
	 * @return
     */
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getProjectStepStatus",method = RequestMethod.GET)
	public List<WSProjectStepStatus> getProjectStepStatusByProId(@RequestParam Long projId){
		//根据projId查询项目步骤信息
		List<ProjectSteps> projectStepses = projectStepsRepository.findByProjectId(projId);
		List<WSProjectStepStatus> result=new ArrayList<>();
		for (ProjectSteps p:projectStepses){
			WSProjectStepStatus wsProjectStepStatus=new WSProjectStepStatus();
			wsProjectStepStatus.setProStepId(p.getId());
			wsProjectStepStatus.setStepId(p.getSteps().getId());
			wsProjectStepStatus.setStatus(p.getDicByStatus().getId());
			wsProjectStepStatus.setStepName(p.getSteps().getId()+"."+p.getSteps().getName());
			result.add(wsProjectStepStatus);
		}
		//如果步骤不全则补全缺少的步骤数据
		if (result!=null&&result.size()>0&&result.size()<12){
			//获取已经存在的步骤的Id集合
			List<Long> steps = getSteps(result);
			for (int i=1;i<=12;i++){
				if (!steps.contains(Long.valueOf(i))){
					WSProjectStepStatus wsProjectStepStatus=new WSProjectStepStatus();
					wsProjectStepStatus.setProStepId(0l);
					wsProjectStepStatus.setStepId(Long.valueOf(i));
					wsProjectStepStatus.setStatus(0l);
					Steps s = stepsRepository.findOne(Long.valueOf(i));
					wsProjectStepStatus.setStepName(i+"."+s.getName());
					result.add(wsProjectStepStatus);
				}
			}
		}
		return result;
	}

	/**
	 * 修改项目步骤状态为结束，保存当前时间为项目步骤结束时间,保存当前用户为员工,新建项目后续步骤并保存当前时间为
	 * 后续步骤开始时间
	 * @param proStepId
	 * @return
     */
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/finishProjectStep",method = RequestMethod.POST)
	public Valid finishProjectStep(@RequestParam Long proStepId){
		Valid v=new Valid();
		ProjectSteps projectSteps = projectStepsRepository.findById(proStepId);
		Dic dic = dicRepository.findById(43l);
		projectSteps.setDicByStatus(dic);
		projectSteps.setEnd(new Date());
		projectSteps.setUsers(securityUtils.getCurrentDBUser());
		//修改项目步骤状态
		projectStepsRepository.save(projectSteps);
		sendEmail("项目步骤结束",projectSteps.getSteps().getEnd_email(),projectSteps);
		//查询后续步骤
		String nextSteps = projectSteps.getSteps().getNextSteps();
		if (nextSteps!=null){
			String[] nextStep = nextSteps.split(",");
			for (String n:nextStep){
				ProjectSteps _p = projectStepsRepository.findByProjIdAndStepId(projectSteps.getCustomer().getId(), Long.valueOf(n));
				if (_p==null){
					Steps steps = stepsRepository.findById(Long.valueOf(n));
					ProjectSteps p=new ProjectSteps();
					p.setSteps(steps);
					p.setCustomer(projectSteps.getCustomer());
					p.setDicByStatus(dicRepository.findById(42l));
					p.setStart(new Date());
					projectStepsRepository.save(p);
					sendEmail("项目步骤开始",steps.getEnd_email(),p);
				}
			}
		}
		v.setValid(Boolean.TRUE);
		return v;
	}

	/**
	 * 修改项目状态
	 * @param projId
	 * @param statusId
     * @return
     */
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/changeProjectStatus",method = RequestMethod.POST)
	public Valid changeProjectStatus(@RequestParam Long projId,@RequestParam Long statusId){
		Valid v=new Valid();
		Customer customer = customerRepository.findOne(projId);
		Dic dic = dicRepository.findOne(statusId);
		customer.setDic(dic);
		customerRepository.save(customer);
		v.setValid(Boolean.TRUE);
		return v;
	}

	private List<Long> getSteps(List<WSProjectStepStatus> list){
		List<Long> stepIds=new ArrayList<>();
		for (WSProjectStepStatus p:list){
			stepIds.add(p.getStepId());
		}
		return stepIds;
	}

	/**
	 * 修改项目是否提交
	 * @param projId
	 * @param commit
     * @return
     */
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/changeProjectCommit",method = RequestMethod.POST)
	public Valid changeProjectCommit(@RequestParam Long projId,@RequestParam Long commit){
		Customer customer = customerRepository.findOne(projId);
		customer.setCommit(commit);
		customerRepository.save(customer);
		Valid v=new Valid();
		v.setValid(true);
		return v;
	}
}
