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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.DicDic;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.repository.DicDicRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.StepsRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSSelectObj;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class StepsController {

	@Autowired
	private UsrService usrService;
	@Autowired
	private DicDicRepository dicDicRepository;
	@Autowired
	private DicRepository dicRepository;
	@Autowired
	private StepsRepository stepsRepository;
	private static final Log logger = LogFactory.getLog(StepsController.class);

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/steps/saveSteps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSteps(@RequestBody Steps steps) {
		Valid v = new Valid();
		if (steps.getId() == null) {
			v.setValid(false);
			v.setMsg("Id不能为空！");
			return v;
		}
		if (steps.getName() == null) {
			v.setValid(false);
			v.setMsg("名字不能为空！");
			return v;
		}
		Steps dbSteps = stepsRepository.findOne(steps.getId());
		if(dbSteps==null)
		{
			dbSteps = new Steps();
			dbSteps.setId(steps.getId());
		}
		dbSteps.setDic(dicRepository.findOne(steps.getDic().getId()));
		dbSteps.setForcastDays(steps.getForcastDays());
		dbSteps.setForm(steps.getForm());
		dbSteps.setLastedDays(steps.getLastedDays());
		dbSteps.setName(steps.getName());
		stepsRepository.save(dbSteps);
		v.setValid(true);
		v.setMsg("保存成功");
		return v;

	}
	
	@RequestMapping(value="/sys/steps/stepsTable", method=RequestMethod.POST)
	public WSTableData stepsTable(
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		
		Pageable pagaable =  new PageRequest(draw,length);
		Page<Steps> page = stepsRepository.findByIdAsc(pagaable);
		List<String[]> lst = new ArrayList<String[]>();
		for(Steps w:page.getContent())
		{
			String[] d = {
					""+w.getId(),
					w.getName(),
					""+w.getForcastDays(),
					""+w.getLastedDays(),
					w.getDic().getCode(),
					w.getForm(),
					""+w.getId()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)page.getTotalElements());
		t.setRecordsFiltered((int)page.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/steps/deleteSteps", method = RequestMethod.GET)
	public Valid deleteSteps(@RequestParam("stepsId") Long stepsId) {
		
		Valid v = new Valid();
		Steps steps = stepsRepository.findOne(stepsId);
		if(steps==null)
		{
			v.setValid(false);
			v.setMsg("不能找到此步骤 Id:" +stepsId);
			return v;
		}
		stepsRepository.delete(stepsId);
		v.setValid(true);
		return v;

	}
}
