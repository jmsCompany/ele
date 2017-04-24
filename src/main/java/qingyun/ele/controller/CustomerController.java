package qingyun.ele.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.CustomerData;
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.DicDic;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSTableData;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserProfile;


@RestController
@Transactional(readOnly=true)
public class CustomerController {
	
	@Autowired private UsrService usrService;
	@Autowired private SubSubLocationRepository subSubLocationRepository;
	@Autowired private CustomerRepository customerRepository;
	@Autowired private DicRepository dicRepository;
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
	public WSTableData logsTable(
			@RequestParam(required=false,value="q") String q,
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		
		Pageable pageable =  new PageRequest(draw,length);
		Page<Customer> customers;
		if(q==null)
		{
			customers = customerRepository.findAll(pageable);
		}
		else
		{
			 q ="'%" + q + "%'";
			customers = customerRepository.findByQ(q, pageable);
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
	
	
	//删除项目，其实是修改项目状态。 {dic:{id:xxx}}
	@Transactional(readOnly = false)
	@RequestMapping(value = "/project/updateProjectStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid updateProjectStatus(@RequestBody Customer customer) {
		Valid v = new Valid();
		Customer dbCustomer = customerRepository.findOne(customer.getId());
		Dic status =dicRepository.findOne(customer.getDic().getId());
		dbCustomer.setDic(status);
		customerRepository.save(dbCustomer);
		v.setValid(true);
		return v;
	}
}
