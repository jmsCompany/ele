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
@Transactional(readOnly = true)
public class SignController {

	@Autowired
	private SignWorkflowRepository signWorkflowRepository;
	@Autowired
	private SignWorkflowStepsRepository signWorkflowStepsRepository;
	@Autowired
	private SignEventRepository signEventRepository;
	@Autowired
	private DicRepository dicRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private SignService signService;

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
		if (signWorkflow == null) {
			v.setValid(false);
			v.setMsg("不能找到此签字 Id:" + signWorkflowId);
			return v;
		}
		if (!signWorkflowStepsRepository.findByIdSignWorkflow(signWorkflowId).isEmpty()) {
			v.setValid(false);
			v.setMsg("不能删除，此签字有签字内容" + signWorkflowId);
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
		Long idUser = signWorkflowSteps.getIdSignatory();
		if (signWorkflowSteps.getId() == null || signWorkflowSteps.getId().equals(0l)) {
			signWorkflowSteps.setId(null);
		}
		if (idUser != null) {
			Users u = usersRepository.findOne(idUser);
			Long idDepartment = u.getDicByDepartment().getId();
			if (idDepartment != null) {
				signWorkflowSteps.setIdDepartment(idDepartment);
			}
		}
		signWorkflowStepsRepository.save(signWorkflowSteps);
		v.setValid(true);
		return v;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/deleteSignWorkflowStep", method = RequestMethod.GET)
	public Valid deleteSignWorkflowStep(@RequestParam("signWorkflowStepId") Long signWorkflowStepId) {

		Valid v = new Valid();
		SignWorkflowSteps signWorkflowSteps = signWorkflowStepsRepository.findOne(signWorkflowStepId);
		if (signWorkflowSteps == null) {
			v.setValid(false);
			v.setMsg("不能找到此签字内容 Id:" + signWorkflowStepId);
			return v;
		}
		signWorkflowStepsRepository.delete(signWorkflowStepId);
		v.setValid(true);
		return v;

	}

	@RequestMapping(value = "/sys/sign/signWorkflowTable", method = RequestMethod.POST)
	public WSTableData signWorkflowTable(@RequestParam Integer start, @RequestParam Integer draw,
			@RequestParam Integer length) {

		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);

		Page<SignWorkflow> signWorkflowData = signWorkflowRepository.findAll(pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for (SignWorkflow w : signWorkflowData.getContent()) {
			String[] d = { "" + w.getId(), w.getName(), w.getForm(), "" + w.getId() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) signWorkflowData.getTotalElements());
		t.setRecordsFiltered((int) signWorkflowData.getTotalElements());
		t.setData(lst);
		return t;
	}

	@RequestMapping(value = "/sys/sign/signWorkflowStepsTable", method = RequestMethod.POST)
	public WSTableData signWorkflowStepsTable(@RequestParam Long idSignWorkflow, @RequestParam Integer start,
			@RequestParam Integer draw, @RequestParam Integer length) {
		int page_num = (start.intValue() / length.intValue()) + 1;
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<SignWorkflowSteps> signWorkflowStepsData = signWorkflowStepsRepository.findByIdSignWorkflow(idSignWorkflow,
				pageable);
		List<String[]> lst = new ArrayList<String[]>();
		// int seq = 1;
		for (SignWorkflowSteps w : signWorkflowStepsData.getContent()) {
			Long idDepartment = w.getIdDepartment();
			String sd = "";
			if (idDepartment != null) {
				Dic depart = dicRepository.findOne(idDepartment);
				if (depart != null) {
					sd = depart.getCode();
				}
			}

			Long idSignatory = w.getIdSignatory();
			String idU = "";
			String sn = "";
			if (idSignatory != null) {
				idU = ""+idSignatory;
				Users signatory = usersRepository.findOne(idSignatory);
				if (signatory != null) {
					sn = signatory.getName();
				}
			}
		
			String pos = "";
			
		//	w.get
			String posId = "";
			if(w.getIdRole()!=null)
			{
				pos = dicRepository.findOne(w.getIdRole()).getCode();
				posId= ""+ w.getIdRole();
			}

			String isLoc ="否";
			if(w.getIsLoc()!=null&&w.getIsLoc().equals(1l))
			{
				isLoc ="是";
			}
					
			String[] d = { "" + w.getId(), "" + w.getLvl(), w.getContent(), pos, isLoc, sd, sn,posId,idU};
			lst.add(d);
			// seq++;
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) signWorkflowStepsData.getTotalElements());
		t.setRecordsFiltered((int) signWorkflowStepsData.getTotalElements());
		t.setData(lst);
		return t;
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/sign/saveSignEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveSignEvent(@RequestBody SignEvent signEvent) {
		Valid v = new Valid();
		if(signEvent.getIdEvent()==null||signEvent.getIdEvent().equals(0l))
		{
			v.setValid(false);
			v.setMsg("该表单没有被保存过，不能签字");
			return v;
		}
	    SignEvent dbSignEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(signEvent.getIdEvent(),
					signEvent.getIdSignWorkflowSteps());
	    //拒绝状态
	   if (dbSignEvent != null) {
		   if(dbSignEvent.getStatus().equals(0l))
		   {
				dbSignEvent.setStatus(signEvent.getStatus());
				dbSignEvent.setRemark(signEvent.getRemark());
				signEventRepository.save(dbSignEvent);
				v.setValid(true);
				return v;
		   }
		   else
		   {
			   v.setValid(false);
			   v.setMsg("你已经同意，不能再次更改");
			return v;
		   }
			
		}
     else {
		signEvent.setSignTime(new Date());
		signEventRepository.save(signEvent);
		v.setValid(true);
		return v;
     }
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/sys/sign/findWSSignEventByEventIdAndSignWorkflowId", method = RequestMethod.GET)
	public List<WSSignEvent> findWSSignEventByEventIdAndSignWorkflowId(@RequestParam("eventId") Long eventId,
			@RequestParam("signWorkflowId") Long signWorkflowId) {
		List<WSSignEvent> ws = new ArrayList<WSSignEvent>();
		List<SignWorkflowSteps> sws = signWorkflowStepsRepository.findByIdSignWorkflow(signWorkflowId);
		for (SignWorkflowSteps s : sws) {
			SignEvent signEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(eventId, signWorkflowId);
			WSSignEvent w = new WSSignEvent();
			w.setIdEvent(eventId);
			w.setIdSignWorkflowSteps(signWorkflowId);
			w.setSignWorkflowSteps(s.getContent());
			w.setIdDepartment(s.getIdDepartment());
			w.setIdSignatory(s.getIdSignatory());
			w.setLvl(s.getLvl());
			if (s.getIdDepartment() != null) {
				Dic depart = dicRepository.findOne(s.getIdDepartment());
				if (depart != null) {
					w.setDepartment(depart.getCode());
				}
			}
			if (s.getIdSignatory() != null) {
				Users signatory = usersRepository.findOne(s.getIdSignatory());
				if (signatory != null) {
					w.setSignatory(signatory.getName());
				}
			}
			if (signEvent != null) {
				w.setId(signEvent.getId());
				w.setStatus(signEvent.getStatus());
				if (signEvent.getStatus().equals(1l)) // 拒绝
				{
					w.setEditable(1l);
				} else if (signEvent.getStatus().equals(2l)) // 签字
				{
					w.setEditable(0l);
				}
				w.setSignTime(signEvent.getSignTime());
				w.setRemark(signEvent.getRemark());

			} else {
				w.setStatus(0l);// 待签字

			}
			w.setEditable(signService.isEditable(s.getId(), eventId, s.getIdSignatory()));

			ws.add(w);
		}
		return ws;
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/sys/sign/signTable", method = RequestMethod.POST)
	public WSTableData signTable(@RequestParam("eventId") Long eventId,
			@RequestParam("signWorkflowId") Long signWorkflowId) {

		WSTableData t = new WSTableData();
		List<String[]> lst = new ArrayList<String[]>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		// List<WSSignEvent> ws = new ArrayList<WSSignEvent>();
		List<SignWorkflowSteps> sws = signWorkflowStepsRepository.findByIdSignWorkflow(signWorkflowId);
		int seq = 1;
		for (SignWorkflowSteps s : sws) {
			//System.out.println("eventId: " + eventId +", signWorkflowId: " + s.getId());
			SignEvent signEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(eventId, s.getId());
			WSSignEvent w = new WSSignEvent();
		//	w.setId(eventId);
			w.setIdEvent(eventId);
			w.setIdSignWorkflowSteps(s.getId());
			w.setSignWorkflowSteps(s.getContent());
			w.setIdDepartment(s.getIdDepartment());
			w.setIdSignatory(s.getIdSignatory());
			w.setLvl(s.getLvl());
			if (s.getIdDepartment() != null) {
				Dic depart = dicRepository.findOne(s.getIdDepartment());
				if (depart != null) {
					w.setDepartment(depart.getCode());
				}
			}
			if (s.getIdSignatory() != null) {
				Users signatory = usersRepository.findOne(s.getIdSignatory());
				if (signatory != null) {
					w.setSignatory(signatory.getName());
				}
			}
			if (signEvent != null) {
				w.setId(signEvent.getId());
				w.setStatus(signEvent.getStatus());
			//	System.out.println("id: " + signEvent.getId() +", status: " + signEvent.getStatus());
				if (signEvent.getStatus().equals(0l)) // 拒绝
				{
					w.setEditable(1l);
				} else if (signEvent.getStatus().equals(1l)) // 签字
				{
					w.setEditable(0l);
				}
				w.setSignTime(signEvent.getSignTime());
				w.setRemark(signEvent.getRemark());

			} else {
				w.setStatus(2l);// 待签字
				w.setId(0l);

			}
			w.setEditable(signService.isEditable(s.getId(), eventId, s.getIdSignatory()));

			// ws.add(w);
			String time = "";
			if (w.getSignTime() != null) {
				time = formatter.format(w.getSignTime());
			}
			String[] d = { "" + seq,  "" + w.getLvl(), ""+w.getIdSignWorkflowSteps(), w.getSignWorkflowSteps(), w.getDepartment(),
					w.getSignatory(), time, ""+w.getStatus(),w.getRemark(),"" + w.getEditable() };
//            System.out.println("顺序 " + seq  + "， 层级： " + w.getLvl() + "， 步骤ID： "+w.getIdSignWorkflowSteps()+ ", 步骤： "+w.getSignWorkflowSteps() +",  部门： "+ w.getDepartment()+
//					",签字人: "+w.getSignatory()+ ", 时间： "+time + " 备注： "+w.getRemark() +"， 可编辑： " + w.getEditable() +", 状态： "+w.getStatus());
			lst.add(d);
			seq++;
		}
		t.setData(lst);
		return t;
	}

}
