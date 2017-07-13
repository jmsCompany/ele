package qingyun.ele.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.Loan;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.CustomerRepository;
import qingyun.ele.repository.LoanRepository;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class ReportController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SecurityUtils securityUtils;
	@Autowired
	private LoanRepository loanRepository;

	private static final Log logger = LogFactory.getLog(ReportController.class);

	// sys/dic/dicSelects dicDicName=Status
	@RequestMapping(value = "/report/report1Table", method = RequestMethod.POST)
	public WSTableData report1Table(@RequestParam(required = false) Long statusId, @RequestParam Integer draw,
			@RequestParam Integer length) {
		Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);
		Page<Customer> customers;

		if (statusId != null) {
			customers = customerRepository.findAllCustomersByRoleIdAnsStatusId(sessionUser.getId(), statusId, pageable);
		} else {
			customers = customerRepository.findAllCustomersByRoleId(sessionUser.getId(), pageable);
		}

		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {

			String code ="";
			if(w.getDic()!=null)
			{
				code =  w.getDic().getCode();
			}
			String[] d = { w.getCode(), w.getName(),code };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	// sys/location/locationSelects
	@RequestMapping(value = "/report/report2Table", method = RequestMethod.POST)
	public WSTableData report2Table(@RequestParam(required = false) Long locationId, @RequestParam Integer draw,
			@RequestParam Integer length) {
		Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);
		Page<Customer> customers;
		if (locationId != null) {
			customers = customerRepository.findByLocationId(locationId, pageable);
		} else {
			customers = customerRepository.findAllCustomersByRoleId(sessionUser.getId(), pageable);
		}

		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {

			SubSubLocation s = w.getSubSubLocation();
			String loc = "";
			if (s != null) {
				loc = s.getSubLocation().getLocation().getName() + "," + s.getSubLocation().getName() + ","
						+ s.getName();
			}
			String[] d = { w.getCode(), w.getName(), loc };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	// sys/location/locationSelects
	// sys/steps/stepSelects
	@RequestMapping(value = "/report/report3Table", method = RequestMethod.POST)
	public WSTableData report3Table(@RequestParam(required = false) Long stepId,
			@RequestParam(required = false) Long locationId, @RequestParam Integer draw, @RequestParam Integer length) {

		Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);

		Page<Customer> customers;

		if (stepId != null) {
			if (locationId != null) {
				customers = customerRepository.findByStepIdAndLocationId(stepId, locationId, pageable);
			} else {
				customers = customerRepository.findByStepId(stepId, sessionUser.getId(), pageable);
			}
		} else {

			if (locationId != null) {
				customers = customerRepository.findByLocationId(locationId, pageable);
			} else {
				customers = customerRepository.findAllCustomersByRoleId(sessionUser.getId(), pageable);
			}
		}

		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {

			String loc = "";
			if (w.getSubSubLocation() != null) {
				loc = w.getSubSubLocation().getSubLocation().getLocation().getName() + ", "
						+ w.getSubSubLocation().getSubLocation().getName() + ", " + w.getSubSubLocation().getName();
			}
			String status = "";
			if (w.getDic() != null) {
				status = w.getDic().getCode();
			}
			String actVol = "", bank = "", dur = "", radio = "", loan = "", loanreturn = "";
			if (w.getActVol() != null)
				actVol = "" + w.getActVol();
			if (w.getDurationLoan() != null)
				dur = "" + w.getDurationLoan();

			Loan loa = loanRepository.findByIdProject(w.getId());
			if (loa != null) {
				if (loa.getBank() != null) {
					bank = loa.getBank();
				}
				if (loa.getRadio() != null) {
					radio = "" + loa.getRadio();
				}
				if (loa.getAmount() != null) {
					loan =""+ loa.getAmount();
				}
			}
			if (w.getMonthLoan() != null) {
				loanreturn = "" + w.getMonthLoan();
			}
			String[] d = { "" + w.getId(), w.getName(), w.getProject(), loc, status, actVol, bank, dur, radio, loan,
					loanreturn };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	// sys/location/locationSelects
	// project/projectSelects
	@RequestMapping(value = "/report/report4Table", method = RequestMethod.POST)
	public WSTableData report4Table(@RequestParam(required = false) Long locationId,
			@RequestParam(required = false) Long projectId, @RequestParam(required = false) String yearmonth,
			@RequestParam Integer draw, @RequestParam Integer length) {

		// Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);

		Page<Customer> customers;

		if (locationId != null) {
			customers = customerRepository.findByLocationId(locationId, pageable);
		} else {
			customers = customerRepository.findByProjectId(projectId, pageable);
		}

		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {

			String unitPrice = "", monthincome = "", subsidyPrice = "", loan = "", loanreturn = "";

			if (w.getUnitPrice() != null) {
				unitPrice = "" + w.getUnitPrice();
			}
			if (w.getMonthIncome() != null) {
				monthincome = "" + w.getMonthIncome();
			}
			Loan loa = loanRepository.findByIdProject(w.getId());
			if (loa != null) {

				if (loa.getAmount() != null) {
					loan =""+ loa.getAmount();
				}
				if (loa.getSubsidyPrice() != null) {
					subsidyPrice = "" + loa.getSubsidyPrice();
				}
			}
			if (w.getMonthLoan() != null) {
				loanreturn = "" + w.getMonthLoan();
			}
			String y = "";
			if (yearmonth != null) {
				y = yearmonth;
			}
			String[] d = { "" + w.getId(), w.getName(), w.getProject(), loan, loanreturn, y, "", unitPrice,
					subsidyPrice, monthincome };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customers.getTotalElements());
		t.setRecordsFiltered((int) customers.getTotalElements());
		t.setData(lst);
		return t;
	}

	// sys/location/locationSelects
	// sys/steps/stepSelects
	@RequestMapping(value = "/report/report5Table", method = RequestMethod.POST)
	public WSTableData report5Table(@RequestParam(required = false) Long stepId,
			@RequestParam(required = false) Long locationId, @RequestParam Integer draw, @RequestParam Integer length) {

		Users sessionUser = securityUtils.getCurrentDBUser();
		// Dic role = sessionUser.getDicByRole();
		Pageable pageable = new PageRequest(draw - 1, length);

		Page<Customer> customers;

		if (stepId != null) {
			if (locationId != null) {
				customers = customerRepository.findByStepIdAndLocationId(stepId, locationId, pageable);
			} else {
				customers = customerRepository.findByStepId(stepId, sessionUser.getId(), pageable);
			}
		} else {

			if (locationId != null) {
				customers = customerRepository.findByLocationId(locationId, pageable);
			} else {
				customers = customerRepository.findAllCustomersByRoleId(sessionUser.getId(), pageable);
			}
		}

		List<String[]> lst = new ArrayList<String[]>();
		for (Customer w : customers.getContent()) {

			String loc = "";
			if (w.getSubSubLocation() != null) {
				loc = w.getSubSubLocation().getSubLocation().getLocation().getName() + ", "
						+ w.getSubSubLocation().getSubLocation().getName() + ", " + w.getSubSubLocation().getName();
			}
			String status = "";
			if (w.getDic() != null) {
				status = w.getDic().getCode();
			}

			String unitPrice = "", devCost = "", unitCost = "", netProfit = "";

			if (w.getUnitPrice() != null) {
				unitPrice = "" + w.getUnitPrice();
			}
			if (w.getUnitCost() != null) {
				unitCost = "" + w.getUnitCost();
			}
			if (w.getDevCost() != null) {
				devCost = "" + w.getDevCost();
			}
			if (w.getNetProfit() != null) {
				netProfit = "" + w.getNetProfit();
			}

			String actVol = "";
			if (w.getActVol() != null)
				actVol = "" + w.getActVol();

			String[] d = { "" + w.getId(), w.getName(), w.getProject(), loc, status, actVol, unitPrice, unitCost,
					devCost, netProfit };
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
