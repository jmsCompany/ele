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
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserProfile;


@RestController
@Transactional(readOnly=true)
public class CustomerController {
	
	@Autowired private UsrService usrService;
	@Autowired private UsersRepository usersRepository;
	@Autowired private CustomerRepository customerRepository;
	private static final Log logger = LogFactory.getLog(CustomerController.class);
	
	

}
