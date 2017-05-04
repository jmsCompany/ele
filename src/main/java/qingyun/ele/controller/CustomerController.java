package qingyun.ele.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.CodeNum;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.Info;
import qingyun.ele.domain.db.Loan;
import qingyun.ele.domain.db.ProjectSteps;
import qingyun.ele.domain.db.So;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.domain.db.TransferSheet;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CodeNumRepository;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.InfoRepository;
import qingyun.ele.repository.LoanRepository;
import qingyun.ele.repository.ProjectStepsRepository;
import qingyun.ele.repository.SoRepository;
import qingyun.ele.repository.StepsRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.repository.TransferSheetRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSProjectSteps;
import qingyun.ele.ws.WSSoTrack;
import qingyun.ele.ws.WSTableData;

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
			dbCustomer.setCreator(securityUtils.getCurrentDBUser().getId());
			CodeNum codeNum = codeNumRepository.findByIdforUpdate(1l);
			Long currentVal = codeNum.getCurr_val();
			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
			dbCustomer.setCode(code);
			codeNum.setCurr_val(currentVal + 1);
			codeNumRepository.save(codeNum);
		} else {
			dbCustomer = customerRepository.findOne(customer.getId());

		}
		// dbCustomer.setCode(customer.getCode());
		dbCustomer.setName(customer.getName());
		dbCustomer.setProject(customer.getProject());
		dbCustomer.setAddress(customer.getAddress());
		dbCustomer.setStart(customer.getStart());
		dbCustomer.setEnd(customer.getEnd());
		dbCustomer.setProcess(customer.getProcess());
		dbCustomer.setSaleMan(customer.getSaleMan());
		Long idDic = customer.getDic().getId();
		if (idDic != null) {
			dbCustomer.setDic(dicRepository.findOne(idDic));
		}
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
		if(dbCustomer.getSoCreationTime()==null)
		{
			dbCustomer.setSoCreationTime(new Date());
		}
		//dbCustomer.set
		// dbCustomer.set
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/project/getSoTrack", method = RequestMethod.GET)
	public WSSoTrack getSoTrack(@RequestParam Long projectId) {
		// Valid v = new Valid();
		WSSoTrack dbCustomer = new WSSoTrack();
		Customer customer = customerRepository.findOne(projectId);
		if (customer != null) {
			dbCustomer.setId(customer.getId());
			dbCustomer.setCode(customer.getCode());
			dbCustomer.setAddress(customer.getSubSubLocation().getName());
			if (customer.getSaleMan() != null) {
				dbCustomer.setSalesMan(usersRepository.findOne(customer.getSaleMan()).getName());
			}
			dbCustomer.setActVol(customer.getActVol());
			dbCustomer.setAgentCost(customer.getAgentCost());
			dbCustomer.setUnitCost(customer.getUnitCost());
			//dbCustomer.setDevCost(customer.getDevCost());
			dbCustomer.setDurationLoan(customer.getDurationLoan());
			dbCustomer.setLoanTime(customer.getLoanTime());
			dbCustomer.setManagementCost(customer.getManagementCost());
			dbCustomer.setMonthIncome(customer.getMonthIncome());
			dbCustomer.setMonthLoan(customer.getMonthLoan());
			
			dbCustomer.setSaleCost(customer.getSaleCost());
			dbCustomer.setUnitCost(customer.getUnitCost());
			dbCustomer.setUnitPrice(customer.getUnitPrice());
		    float agent =0f;
		    if(customer.getAgentCost()!=null)
		    {
		    	agent = customer.getAgentCost();
		    }
		    
		    float saleCost =0f;
		    if(customer.getSaleCost()!=null)
		    {
		    	saleCost = customer.getSaleCost();
		    }
		    
		    float manage =0f;
		    if(customer.getManagementCost()!=null)
		    {
		    	manage = customer.getManagementCost();
		    }
		    
		    Float devCost = agent +saleCost+manage;
			dbCustomer.setDevCost(devCost);
			
			Float actVol = 0f;
			if(customer.getActVol()!=null)
			{
				actVol = customer.getActVol();
			}
			Float unitPrice = 0f;
			if(customer.getUnitPrice()!=null)
			{
				unitPrice = customer.getUnitPrice();
			}
			
			Float unitCost = 0f;
			if(customer.getUnitCost()!=null)
			{
				unitCost = customer.getUnitCost();
			}
			long dur = 0l;
			if(customer.getDurationLoan()!=null)
			{
				dur = customer.getDurationLoan();
			}
			Float monIncome = 0f;
			if(customer.getMonthIncome()!=null)
			{
				monIncome = customer.getMonthIncome();
				
			}
			Float monLoan = 0f;
			if(customer.getMonthLoan()!=null)
			{
				monLoan = customer.getMonthLoan();
				
			}
			//净利=实际容量*（销售价格-建设成本）-开发费用总额+（贷款期限*（预计每月售电收入-每月还贷金额））
			
			Float netProfit = actVol*(unitPrice-unitCost)-devCost+(dur*(monIncome-monLoan));
			dbCustomer.setNetProfit(netProfit);
			dbCustomer.setName(customer.getName());
		}

		return dbCustomer;
	}

	@RequestMapping(value = "/project/projectTable", method = RequestMethod.POST)
	public WSTableData projectTable(@RequestParam(required = false, value = "q") String q, @RequestParam Integer draw,
			@RequestParam Integer length) {
		Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);
		Page<Customer> customers;
		if (q == null) {
			customers = customerRepository.findAllCustomers(sessionUser.getId(), pageable);
		} else {
			customers = customerRepository.findByQ(q, sessionUser.getId(), pageable);
		}
		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {
			SubSubLocation s = w.getSubSubLocation();
			String loc = s.getSubLocation().getLocation().getName() + "," + s.getSubLocation().getName() + ","
					+ s.getName();
			String saleMan ="";
			if(w.getSaleMan()!=null)
			{
				saleMan = usersRepository.findOne(w.getSaleMan()).getName();
			}
			String[] d = { "" + w.getId(), w.getCode(), w.getName(), w.getAddress(), w.getProject(), loc,
					w.getDic().getCode(),saleMan, "" + w.getId(), "" + w.getId(), "" + w.getId() };
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

	@RequestMapping(value = "/project/projectStepsTable", method = RequestMethod.POST)
	public WSTableData projectStepsTable(@RequestParam Long projectId, @RequestParam Integer start,
			@RequestParam Integer draw, @RequestParam Integer length) {

		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<Steps> steps = stepsRepository.findAll(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Steps w : steps.getContent()) {

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
				if(projectSteps.getActDays()!=null)
				{
					act_days=""+ projectSteps.getActDays();
				}
				
				
				if (projectSteps.getDicByStatus() != null) {
					status = projectSteps.getDicByStatus().getCode();
				}

				remark = projectSteps.getRemark();
				if (projectSteps.getDicByDepartment() != null) {
					depart = projectSteps.getDicByDepartment().getCode();
				}

				if (projectSteps.getDicByProgress() != null) {
					delay = projectSteps.getDicByProgress().getCode();
				}
				if (projectSteps.getUsers() != null) {
					emp = projectSteps.getUsers().getName();
				}
			}

			String[] d = { "" + id, "" + w.getId(), w.getName(), "" + w.getForcastDays(), "" + w.getLastedDays(),
					act_start, act_end, act_days, delay, status, remark, depart, emp, form, "" + id };
			lst.add(d);
		}

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
		ProjectSteps dbProjectSteps;
		// create new
		if (wsProjectSteps.getId() == null || wsProjectSteps.getId().equals(0l)) {
			dbProjectSteps = new ProjectSteps();
			// CodeNum codeNum = codeNumRepository.findOne(2l);
			// Long currentVal = codeNum.getCurr_val();
			// String code = codeNum.getPrefix()+String.format("%08d",
			// currentVal);
			// dbProjectSteps.setCode(code);
			// codeNum.setCurr_val(currentVal+1);
			// codeNumRepository.save(codeNum);
		} else {
			dbProjectSteps = projectStepsRepository.findOne(wsProjectSteps.getId());
		}
		dbProjectSteps.setCustomer(customerRepository.findOne(wsProjectSteps.getCustomerId()));
		if (wsProjectSteps.getDepartmentId() != null) {
			dbProjectSteps.setDicByDepartment(dicRepository.findOne(wsProjectSteps.getDepartmentId()));
		}
		if (wsProjectSteps.getIdProgress() != null) {
			dbProjectSteps.setDicByProgress(dicRepository.findOne(wsProjectSteps.getIdProgress()));
		}
		//dbProjectSteps.setActDays(wsProjectSteps.getActDays());
		dbProjectSteps.setEnd(wsProjectSteps.getEnd());
		dbProjectSteps.setForcastDays(wsProjectSteps.getForcastDays());
		dbProjectSteps.setLastedDays(wsProjectSteps.getLastedDays());
		dbProjectSteps.setName(wsProjectSteps.getName());
		dbProjectSteps.setRemark(wsProjectSteps.getRemark());
		dbProjectSteps.setStart(wsProjectSteps.getStart());
		if(wsProjectSteps.getStart()!=null&&wsProjectSteps.getEnd()!=null)
		{
			long days = (wsProjectSteps.getEnd().getTime()-wsProjectSteps.getStart().getTime())/(1000*3600*24);
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

		projectStepsRepository.save(dbProjectSteps);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveForm1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveForm1(@RequestBody Info info) {
		Valid v = new Valid();
		Long projectId = info.getIdProject();
		Info dbInfo = infoRepository.findByIdProject(projectId);
		if (dbInfo != null) {
			info.setId(dbInfo.getId());
			info.setCode(dbInfo.getCode());
		} else {
			CodeNum codeNum = codeNumRepository.findByIdforUpdate(3l);
			Long currentVal = codeNum.getCurr_val();
			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
			info.setCode(code);
			codeNum.setCurr_val(currentVal + 1);
			codeNumRepository.save(codeNum);
		}

		infoRepository.save(info);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getForm1", method = RequestMethod.GET)
	public Info getForm1(@RequestParam Long projectId) {

		Customer c = customerRepository.findOne(projectId);
		Info dbInfo = infoRepository.findByIdProject(projectId);
		if (dbInfo == null) {
			dbInfo = new Info();
		}
		dbInfo.setC1(c.getAddress());
		dbInfo.setC2(c.getProject());
		dbInfo.setC3(c.getName());
		//dbInfo.setc
		return dbInfo;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveForm2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveForm2(@RequestBody TransferSheet transferSheet) {
		Valid v = new Valid();

		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(transferSheet.getIdProject());
		if (dbTransferSheet != null) {
			transferSheet.setId(dbTransferSheet.getId());
			transferSheet.setCode(dbTransferSheet.getCode());
		} else {
			CodeNum codeNum = codeNumRepository.findByIdforUpdate(4l);
			Long currentVal = codeNum.getCurr_val();
			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
			transferSheet.setCode(code);
			codeNum.setCurr_val(currentVal + 1);
			codeNumRepository.save(codeNum);
		}

		transferSheetRepository.save(transferSheet);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/getForm2", method = RequestMethod.GET)
	public TransferSheet getForm2(@RequestParam Long projectId) {

		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(projectId);
		if (dbTransferSheet == null) {
			dbTransferSheet = new TransferSheet();
		}
		Customer c = customerRepository.findOne(projectId);
		dbTransferSheet.setC1(c.getAddress());
		dbTransferSheet.setC2(c.getProject());
		dbTransferSheet.setC3(c.getName());
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

		else {
			CodeNum codeNum = codeNumRepository.findByIdforUpdate(5l);
			Long currentVal = codeNum.getCurr_val();
			String code = codeNum.getPrefix() + String.format("%08d", currentVal);
			loan.setCode(code);
			codeNum.setCurr_val(currentVal + 1);
			codeNumRepository.save(codeNum);
		}

		loanRepository.save(loan);
		v.setValid(true);
		return v;
	}

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
		
		dbLoan.setDuration(c.getDurationLoan());
		dbLoan.setAmountPermonth(c.getMonthLoan());
		dbLoan.setPaymentTime(c.getLoanTime());
		dbLoan.setIdProject(c.getId());
		//private Long capacity;//实际容量 form2
		
		TransferSheet dbTransferSheet = transferSheetRepository.findByIdProject(projectId);
		if(dbTransferSheet!=null)
		{
			dbLoan.setCapacity(dbTransferSheet.getC4());
		}
	

		
		
		return dbLoan;

	}

	// @Transactional(readOnly = false)
	// @RequestMapping(value = "/project/saveSoTrack", method =
	// RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// public Valid saveSoTrack(@RequestBody So so) {
	// Valid v = new Valid();
	// Long projectId = so.getCustomer().getId();
	// So dbSo = soRepository.findByIdProject(projectId);
	// if (dbSo == null) {
	// so.setCustomer(customerRepository.findOne(projectId));
	// }
	// soRepository.save(so);
	// v.setValid(true);
	// return v;
	// }

}
