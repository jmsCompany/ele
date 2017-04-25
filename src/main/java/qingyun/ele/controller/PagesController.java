package qingyun.ele.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qingyun.ele.domain.db.DicDic;
import qingyun.ele.domain.db.Pages;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.PagesRepository;
import qingyun.ele.repository.RolePagesRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserProfile;


@RestController
@Transactional(readOnly=true)
public class PagesController {
	
	@Autowired private UsrService usrService;
	@Autowired private UsersRepository usersRepository;
	@Autowired private CustomerRepository customerRepository;
	private static final Log logger = LogFactory.getLog(PagesController.class);
	@Autowired private PagesRepository pagesRepository;
	@Autowired private RolePagesRepository rolePagesRepository;
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/pages/savePage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid savePage(@RequestBody Pages pages) {
		Valid v = new Valid();

		if (pages.getName() == null) {
			v.setValid(false);
			v.setMsg("名字不能为空！");
			return v;
		}

		// create new
		if (pages.getId() == null || pages.getId().equals(0l)) {
			Pages dbPages = pagesRepository.findByName(pages.getName());
			if (dbPages != null) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}
		} else {
			
			Pages dbPages = pagesRepository.findByName(pages.getName());
			if (dbPages != null && !dbPages.getId().equals(pages.getId())) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}

		}
		pagesRepository.save(pages);
		v.setValid(true);
		return v;

	}
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/pages/deletePages", method = RequestMethod.GET)
	public Valid deletePages(@RequestParam("pagesId") Long pagesId) {
		
		Valid v = new Valid();
		Pages pages = pagesRepository.findOne(pagesId);
		if(pages==null)
		{
			v.setValid(false);
			v.setMsg("不能找到此页面" +pagesId);
			return v;
		}
		if(!pages.getRolePageses().isEmpty())
		{
			v.setValid(false);
			v.setMsg("页面已经被使用，不能删除" +pagesId);
			return v;
		}
		pagesRepository.delete(pagesId);
		v.setValid(true);
		return v;

	}
	

}
