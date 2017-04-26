package qingyun.ele.controller;

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
import qingyun.ele.domain.db.Dic;
import qingyun.ele.domain.db.SignEvent;
import qingyun.ele.domain.db.SignWorkflow;
import qingyun.ele.domain.db.SignWorkflowSteps;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.SignEventRepository;
import qingyun.ele.repository.SignWorkflowRepository;
import qingyun.ele.repository.SignWorkflowStepsRepository;
import qingyun.ele.repository.UsersRepository;
import qingyun.ele.service.SignService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSSignEvent;
import qingyun.ele.ws.WSTableData;


@RestController
@Transactional(readOnly=true)
public class SignController {
	
	@Autowired private SignWorkflowRepository signWorkflowRepository;
	@Autowired private SignWorkflowStepsRepository signWorkflowStepsRepository;
	@Autowired private SignEventRepository signEventRepository;
	@Autowired private DicRepository dicRepository;
	@Autowired private UsersRepository usersRepository;
	@Autowired private SignService signService;
	
	private static final Log logger = LogFactory.getLog(SignController.class);
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/saveSignWorkflow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSignWorkflow(@RequestBody SignWorkflow signWorkflow) {
		Valid v = new Valid();
		if (signWorkflow.getName() == null) {
			v.setValid(false);
			v.setMsg("名字不能为空！");
			return v;
		}
		// create new
		if (signWorkflow.getId() == null || signWorkflow.getId().equals(0l)) {
			SignWorkflow dbSignWorkflow = signWorkflowRepository.findByName(signWorkflow.getName());
			if (dbSignWorkflow != null) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}
		} else {
			SignWorkflow dbSignWorkflow = signWorkflowRepository.findByName(signWorkflow.getName());
			if (dbSignWorkflow != null && !dbSignWorkflow.getId().equals(signWorkflow.getId())) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}
		}
		
		signWorkflowRepository.save(signWorkflow);
		v.setValid(true);
		return v;
	}

	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/deleteSignWorkflow", method = RequestMethod.GET)
	public Valid deleteSignWorkflow(@RequestParam("signWorkflowId") Long signWorkflowId) {
		
		Valid v = new Valid();
		SignWorkflow signWorkflow = signWorkflowRepository.findOne(signWorkflowId);
		if(signWorkflow==null)
		{
			v.setValid(false);
			v.setMsg("不能找到此签字 Id:" +signWorkflowId);
			return v;
		}
		if(!signWorkflowStepsRepository.findByIdSignWorkflow(signWorkflowId).isEmpty())
		{
			v.setValid(false);
			v.setMsg("不能删除，此签字有签字内容" +signWorkflowId);
			return v;
		}
		
		signWorkflowRepository.delete(signWorkflowId);
		v.setValid(true);
		return v;

	}
	
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/saveSignWorkflowSteps", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSignWorkflowSteps(@RequestBody SignWorkflowSteps signWorkflowSteps) {
		Valid v = new Valid();
		signWorkflowStepsRepository.save(signWorkflowSteps);
		v.setValid(true);
		return v;
	}
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/deleteSignWorkflowStep", method = RequestMethod.GET)
	public Valid deleteSignWorkflowStep(@RequestParam("signWorkflowStepId") Long signWorkflowStepId) {
		
		Valid v = new Valid();
		SignWorkflowSteps signWorkflowSteps = signWorkflowStepsRepository.findOne(signWorkflowStepId);
		if(signWorkflowSteps==null)
		{
			v.setValid(false);
			v.setMsg("不能找到此签字内容 Id:" +signWorkflowStepId);
			return v;
		}
		signWorkflowStepsRepository.delete(signWorkflowStepId);
		v.setValid(true);
		return v;

	}
	
	
	@RequestMapping(value="/sys/sign/signWorkflowTable", method=RequestMethod.POST)
	public WSTableData signWorkflowTable(
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		Pageable pageable =  new PageRequest(draw-1,length);
		Page<SignWorkflow> signWorkflowData =signWorkflowRepository.findAll(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for(SignWorkflow w:signWorkflowData.getContent())
		{
			String[] d = {
					""+w.getId(),
					w.getName(),
					""+w.getId()
					};
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)signWorkflowData.getTotalElements());
		t.setRecordsFiltered((int)signWorkflowData.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
	
	@RequestMapping(value="/sys/sign/signWorkflowStepsTable", method=RequestMethod.POST)
	public WSTableData signWorkflowStepsTable(
			@RequestParam Long idSignWorkflow,
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		Pageable pageable =  new PageRequest(draw-1,length);
		Page<SignWorkflowSteps> signWorkflowStepsData =signWorkflowStepsRepository.findByIdSignWorkflow(idSignWorkflow, pageable);
		List<String[]> lst = new ArrayList<String[]>();
		int seq =1;
		for(SignWorkflowSteps w:signWorkflowStepsData.getContent())
		{
            Long idDepartment = w.getIdDepartment();
            String sd ="";
            if(idDepartment!=null)
            {
            	Dic depart =dicRepository.findOne(idDepartment);
            	if(depart!=null)
            	{
            		sd = depart.getCode();
            	}
            }
            
            Long idSignatory = w.getIdSignatory();
            String sn ="";
            if(idSignatory!=null)
            {
            	Users signatory =usersRepository.findOne(idSignatory);
            	if(signatory!=null)
            	{
            		sn = signatory.getName();
            	}
            }
            
			String[] d = {
					""+seq,
					""+w.getLvl(),
					w.getContent(),
					sd,
					sn,
					""+w.getId()
					};
			lst.add(d);
			seq++;
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int)signWorkflowStepsData.getTotalElements());
		t.setRecordsFiltered((int)signWorkflowStepsData.getTotalElements());
	    t.setData(lst);
	    return t;
	}
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/saveSignEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSignEvent(@RequestBody SignEvent signEvent) {
		Valid v = new Valid();
		if (signEvent.getId() == null || signEvent.getId().equals(0l)) 
		{
			SignEvent dbSignEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(signEvent.getIdEvent(), signEvent.getIdSignWorkflowSteps());
			if(dbSignEvent!=null)
			{
				v.setValid(false);
				v.setMsg("该内容已经签字");
				return v;
			}
			
		}
		else
		{
			SignEvent dbSignEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(signEvent.getIdEvent(), signEvent.getIdSignWorkflowSteps());
			if(!dbSignEvent.getId().equals(signEvent.getId()))
			{
				v.setValid(false);
				v.setMsg("该内容已经签字");
				return v;
			}
		}
		signEvent.setSignTime(new Date());
		signEventRepository.save(signEvent);
		v.setValid(true);
		return v;
	}
	
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/sys/sign/findWSSignEventByEventIdAndSignWorkflowId", method = RequestMethod.GET)
	public List<WSSignEvent> findWSSignEventByEventIdAndSignWorkflowId(@RequestParam("eventId") Long eventId,@RequestParam("signWorkflowId") Long signWorkflowId)
	{
		List<WSSignEvent> ws = new ArrayList<WSSignEvent>();
	   	List<SignWorkflowSteps>  sws = signWorkflowStepsRepository.findByIdSignWorkflow(signWorkflowId);
	   	for(SignWorkflowSteps s:sws)
		{
			SignEvent signEvent =signEventRepository.findByIdEventAndIdSignWorkflowSteps(eventId, signWorkflowId);
			WSSignEvent w  = new WSSignEvent();
			w.setIdEvent(eventId);
			w.setIdSignWorkflowSteps(signWorkflowId);
			w.setSignWorkflowSteps(s.getContent());
			w.setIdDepartment(s.getIdDepartment());
			w.setIdSignatory(s.getIdSignatory());
			w.setLvl(s.getLvl());
			if(s.getIdDepartment()!=null)
			{
				Dic depart =dicRepository.findOne(s.getIdDepartment());
            	if(depart!=null)
            	{
            		w.setDepartment(depart.getCode());
            	}
			}
			if(s.getIdSignatory()!=null)
			{
				Users signatory =usersRepository.findOne(s.getIdSignatory());
            	if(signatory!=null)
            	{
            		w.setSignatory(signatory.getName());
            	}	
			}
			if(signEvent!=null)
			{
				w.setId(signEvent.getId());
				w.setStatus(signEvent.getStatus());
				if(signEvent.getStatus().equals(1l)) //拒绝
				{
					w.setEditable(1l);
				}
				else if(signEvent.getStatus().equals(2l)) //签字
				{
					w.setEditable(0l);
				}
				w.setSignTime(signEvent.getSignTime());
				w.setRemark(signEvent.getRemark());
					
			}else
			{
				w.setStatus(0l);//待签字
			    
			}
		   w.setEditable(signService.isEditable(s.getId(), eventId));
		
			ws.add(w);
		}
	    return ws;
	}
	
}
