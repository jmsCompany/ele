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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.domain.db.CustomerData;
import qingyun.ele.repository.CustomerDataRepository;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSArea;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class CustomerDataController {

	@Autowired
	private CustomerDataRepository customerDataRepository;
	private static final Log logger = LogFactory.getLog(CustomerDataController.class);

	@Transactional(readOnly = false)
	@RequestMapping(value = "/info/customer/saveCustomerData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveCustomerData(@RequestBody CustomerData customerData) {
		Valid v = new Valid();
		customerDataRepository.save(customerData);
		v.setValid(true);
		return v;
	}
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/info/customer/areaData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public WSArea areaData() {
		WSArea v = new WSArea();
		String[] date ={"1968/10/4", "1968/10/5", "1968/10/6", "1968/10/7", "1968/10/8", "1968/10/9", "1968/10/10", "1968/10/11", "1968/10/12", "1968/10/13", "1968/10/14", "1968/10/15", "1968/10/16", "1968/10/17", "1968/10/18", "1968/10/19", "1968/10/20", "1968/10/21", "1968/10/22", "1968/10/23", "1968/10/24", "1968/10/25", "1968/10/26", "1968/10/27", "1968/10/28", "1968/10/29", "1968/10/30", "1968/10/31", "1968/11/1", "1968/11/2", "1968/11/3", "1968/11/4", "1968/11/5", "1968/11/6", "1968/11/7", "1968/11/8", "1968/11/9", "1968/11/10", "1968/11/11", "1968/11/12", "1968/11/13", "1968/11/14", "1968/11/15", "1968/11/16", "1968/11/17", "1968/11/18", "1968/11/19", "1968/11/20", "1968/11/21", "1968/11/22", "1968/11/23", "1968/11/24", "1968/11/25", "1968/11/26", "1968/11/27", "1968/11/28", "1968/11/29", "1968/11/30", "1968/12/1", "1968/12/2", "1968/12/3", "1968/12/4", "1968/12/5", "1968/12/6", "1968/12/7", "1968/12/8", "1968/12/9", "1968/12/10", "1968/12/11", "1968/12/12", "1968/12/13", "1968/12/14", "1968/12/15", "1968/12/16", "1968/12/17", "1968/12/18", "1968/12/19", "1968/12/20", "1968/12/21", "1968/12/22", "1968/12/23", "1968/12/24", "1968/12/25", "1968/12/26", "1968/12/27", "1968/12/28", "1968/12/29", "1968/12/30", "1968/12/31", "1969/1/1", "1969/1/2", "1969/1/3", "1969/1/4", "1969/1/5", "1969/1/6", "1969/1/7", "1969/1/8", "1969/1/9", "1969/1/10", "1969/1/11"};
		Integer[] data ={92, 89, 83, 74, 69, 75, 73, 71, 62, 58, 49, 39, 32, 27, 32, 24, 17, 10, 19, 15, 20, 16, 18, 19, 18, 11, 15, 10, 14, 9, 5, 4, -4, 5, 12, 15, 21, 17, 17, 27, 19, 10, 14, 21, 24, 27, 24, 34, 33, 39, 34, 44, 48, 44, 46, 39, 43, 48, 43, 39, 38, 40, 36, 40, 49, 50, 59, 65, 70, 71, 68, 64, 68, 72, 75, 67, 65, 60, 61, 69, 69, 63, 73, 66, 59, 60, 51, 54, 61, 69, 63, 62, 71, 63, 63, 65, 59, 51, 57, 67};
		
		v.setData(data);
		v.setDate(date);
		v.setValid(true);
		return v;
	}
	
	

	@RequestMapping(value = "/info/customer/customerDataTable", method = RequestMethod.POST)
	public WSTableData customerDataTable(@RequestParam Integer start, @RequestParam Integer draw,
			@RequestParam Integer length) {
		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);

		Page<CustomerData> customerData = customerDataRepository.findByIdDesc(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for (CustomerData w : customerData.getContent()) {
			String[] d = {w.getC1(),w.getC2(),w.getC3(),w.getC4(),w.getC5(),w.getC6(),w.getC7(),
					w.getC8(),w.getC9(),w.getC10(),w.getC11(),w.getC12(),w.getC13(),w.getC14(),
					w.getC15(),w.getC16(),w.getC17(),w.getC18(),w.getC19(),w.getC20(),w.getC21(),w.getC22()};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) customerData.getTotalElements());
		t.setRecordsFiltered((int) customerData.getTotalElements());
		t.setData(lst);
		return t;
	}

}
