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
import qingyun.ele.domain.db.Steps;
import qingyun.ele.repository.CustomerDataRepository;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSTableData;


@RestController
@Transactional(readOnly=true)
public class CustomerDataController {
	
	@Autowired private CustomerDataRepository customerDataRepository;
	private static final Log logger = LogFactory.getLog(CustomerDataController.class);
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/info/customer/saveCustomerData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveCustomerData(@RequestBody CustomerData customerData) {
		Valid v = new Valid();
		customerDataRepository.save(customerData);
		v.setValid(true);
		return v;
	}

	@RequestMapping(value="/info/customer/customerDataTable", method=RequestMethod.POST)
	public WSTableData customerDataTable(
			@RequestParam Integer start,@RequestParam Integer draw,@RequestParam Integer length) 
	{
		int  page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);

		Page<CustomerData> customerData =customerDataRepository.findByIdDesc(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for(CustomerData w:customerData.getContent())
		{
			String[] d = {
					w.getCustomerCode(),
					w.getCustomerName(),
					w.getCustomerCode(),
					w.getTimeDuration(),
					""+w.getCapacity()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)customerData.getTotalElements());
		t.setRecordsFiltered((int)customerData.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
}
