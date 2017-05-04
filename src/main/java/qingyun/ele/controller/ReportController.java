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
import qingyun.ele.domain.db.Dic;
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
public class ReportController {

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
	private static final Log logger = LogFactory.getLog(ReportController.class);

	
	@RequestMapping(value = "/report/report1Table", method = RequestMethod.POST)
	public WSTableData report1Table(
			@RequestParam Long  statusId,
			@RequestParam Integer draw,
			@RequestParam Integer length) {
		Users sessionUser = securityUtils.getCurrentDBUser();
		//Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);
		Page<Customer> customers = customerRepository.findAllCustomersByRoleIdAnsStatusId(sessionUser.getId(), statusId, pageable);
		
		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {
		
			String[] d = { w.getCode(), w.getName(), w.getDic().getCode() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	@RequestMapping(value = "/report/report2Table", method = RequestMethod.POST)
	public WSTableData report2Table(
			@RequestParam Long  locationId,
			@RequestParam Integer draw,
			@RequestParam Integer length) {
		//Users sessionUser = securityUtils.getCurrentDBUser();
		//Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);
		Page<Customer> customers = customerRepository.findByLocationId(locationId, pageable);
		
		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {
		
			SubSubLocation s = w.getSubSubLocation();
			String loc = "";
			if(s!=null)
			{
				loc = s.getSubLocation().getLocation().getName() + ","
						+ s.getSubLocation().getName() + "," + s.getName();
			}
			String[] d = { w.getCode(), w.getName(),loc };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}
	
}
