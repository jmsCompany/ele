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
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.ProjectSteps;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.ProjectStepsRepository;
import qingyun.ele.repository.StepsRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSProjectSteps;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly=true)
public class CustomerController {
	
	@Autowired private UsrService usrService;
	@Autowired private SubSubLocationRepository subSubLocationRepository;
	@Autowired private CustomerRepository customerRepository;
	@Autowired private DicRepository dicRepository;
	@Autowired private StepsRepository stepsRepository;
	@Autowired private ProjectStepsRepository projectStepsRepository;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private UsersRepository usersRepository;
	private static final Log logger = LogFactory.getLog(CustomerController.class);
	
	
	//界面中需要添加项目开始时间，项目结束时间。
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
		} else {
		   dbCustomer = customerRepository.findOne(customer.getId());
	
		}
		dbCustomer.setCode(customer.getCode());
		dbCustomer.setName(customer.getName());
		dbCustomer.setProject(customer.getProject());
		dbCustomer.setAddress(customer.getAddress());
		dbCustomer.setStart(customer.getStart());
		dbCustomer.setEnd(customer.getEnd());
		Long idDic = customer.getDic().getId();
		if(idDic!=null)
		{
			dbCustomer.setDic(dicRepository.findOne(idDic));
		}
		Long idSubSubLocation = customer.getSubSubLocation().getId();
		if(idSubSubLocation!=null)
		{
			dbCustomer.setSubSubLocation(subSubLocationRepository.findOne(idSubSubLocation));
		}
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}
	
	
	@RequestMapping(value="/project/projectTable", method=RequestMethod.POST)
	public WSTableData projectTable(
			@RequestParam(required=false,value="q") String q,
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		Users sessionUser = securityUtils.getCurrentDBUser();
		//Dic role = sessionUser.getDicByRole();
		Pageable pageable =  new PageRequest(draw-1,length);
		Page<Customer> customers;
		if(q==null)
		{
			customers = customerRepository.findAllCustomers(sessionUser.getId(),pageable);
		}
		else
		{
			customers = customerRepository.findByQ(q,sessionUser.getId(), pageable);
		}
		List<String[]> lst = new ArrayList<String[]>();
		for(Customer w:customers.getContent())
		{
			SubSubLocation s = w.getSubSubLocation();
			String loc = s.getSubLocation().getLocation().getName()+","+ s.getSubLocation().getName()+","+s.getName();
			String[] d = {
					""+w.getId(),
					w.getCode(),
					w.getName(),
					w.getAddress(),
					w.getProject(),
					loc,
					w.getDic().getCode(),
					""+w.getId(),
					""+w.getId(),
					""+w.getId()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)customers.getTotalElements());
		t.setRecordsFiltered((int)customers.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
	
	//删除项目，其实是修改项目Deleted
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
	

	@RequestMapping(value="/project/projectStepsTable", method=RequestMethod.POST)
	public WSTableData projectStepsTable(
			@RequestParam Long projectId,
			@RequestParam Integer start,
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		
		
		int  page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<Steps> steps = stepsRepository.findAll(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		for(Steps w:steps.getContent())
		{
			
			String act_start="";
			String act_end="";
			String act_days="";
			String delay="";
			String status="";
			String remark="";
			String depart="";
			String emp="";
			String form = w.getForm();
			ProjectSteps projectSteps = projectStepsRepository.findByProjectIdAndStepId(projectId, w.getId());
			if(projectSteps!=null)
			{
				if(projectSteps.getStart()!=null)
				{
				   act_start=formatter.format(projectSteps.getStart());
				}
				if(projectSteps.getEnd()!=null)
				{
				   act_end=formatter.format(projectSteps.getEnd());
				}
			
			    act_days=""+projectSteps.getLastedDays();
			    if(projectSteps.getDicByStatus()!=null)
			    {
			    	 status=projectSteps.getDicByStatus().getCode();
			    }
			   
			    remark=projectSteps.getRemark();
			    if(projectSteps.getDicByDepartment()!=null)
			    {
			    	depart=projectSteps.getDicByDepartment().getCode();
			    }
			 
			    if(projectSteps.getDicByProgress()!=null)
			    {
			    	delay=projectSteps.getDicByProgress().getCode();
			    }
			    if(projectSteps.getUsers()!=null)
			    {
			    	emp = projectSteps.getUsers().getName();
			    }
			}
			
			String[] d = {
	                w.getName(),
					""+w.getForcastDays(),
					""+w.getLastedDays(),
					act_start,
					act_end,
					act_days,
					delay,
					status,
					remark,
					depart,
					emp,
					form,
					""+w.getId()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)steps.getTotalElements());
		t.setRecordsFiltered((int)steps.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	

	//实际开始，实际结束，进度。状态，备注
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/saveProjectSteps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveProjectSteps(@RequestBody WSProjectSteps wsProjectSteps) {
		Valid v = new Valid();
		ProjectSteps dbProjectSteps;
		// create new
		if (wsProjectSteps.getId() == null || wsProjectSteps.getId().equals(0l)) {
			dbProjectSteps = new ProjectSteps();
		} else {
			dbProjectSteps = projectStepsRepository.findOne(wsProjectSteps.getId());
		}
		dbProjectSteps.setCustomer(customerRepository.findOne(wsProjectSteps.getCustomerId()));
		if(wsProjectSteps.getDepartmentId()!=null)
		{
			dbProjectSteps.setDicByDepartment(dicRepository.findOne(wsProjectSteps.getDepartmentId()));
		}
		if(wsProjectSteps.getIdProgress()!=null)
		{
			dbProjectSteps.setDicByProgress(dicRepository.findOne(wsProjectSteps.getIdProgress()));
		}
		dbProjectSteps.setActDays(wsProjectSteps.getActDays());
		dbProjectSteps.setEnd(wsProjectSteps.getEnd());
		dbProjectSteps.setForcastDays(wsProjectSteps.getForcastDays());
		dbProjectSteps.setName(wsProjectSteps.getName());
		dbProjectSteps.setRemark(wsProjectSteps.getRemark());
		dbProjectSteps.setStart(wsProjectSteps.getStart());
		if(wsProjectSteps.getStepId()!=null)
		{
			dbProjectSteps.setSteps(stepsRepository.findOne(wsProjectSteps.getStepId()));
		}
		if(wsProjectSteps.getUserId()!=null)
		{
			dbProjectSteps.setUsers(usersRepository.findOne(wsProjectSteps.getUserId()));
		}
		
		if(wsProjectSteps.getStatusId()!=null)
		{
			dbProjectSteps.setDicByStatus(dicRepository.findOne(wsProjectSteps.getStatusId()));
		}
		projectStepsRepository.save(dbProjectSteps);
		v.setValid(true);
		return v;
	}
	
	
}
