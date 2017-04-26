package qingyun.ele.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.Logs;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.LogsRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSTableData;
import qingyun.ele.ws.WSUser;
import qingyun.ele.ws.WSUserPassword;
import qingyun.ele.ws.WSUserProfile;

@RestController
@Transactional(readOnly=true)
public class LogController {
	
	@Autowired private LogsRepository logsRepository;
	private static final Log logger = LogFactory.getLog(LogController.class);
	

	@RequestMapping(value="/info/log/logsTable", method=RequestMethod.POST)
	public WSTableData logsTable(
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		
		Pageable pageable =  new PageRequest(draw-1,length);
		Page<Logs> logs =logsRepository.findByIdDesc(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for(Logs w:logs.getContent())
		{
			Users u = w.getUsers();
			String name = (u==null)?"":u.getUsername();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			
			String[] d = {
					formatter.format(w.getTime()),
					name,
					w.getIp(),
					w.getUrl()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)logs.getTotalElements());
		t.setRecordsFiltered((int)logs.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
}
