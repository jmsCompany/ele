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
import qingyun.ele.repository.DicDicRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSSelectObj;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class DicController {

	@Autowired
	private UsrService usrService;
	@Autowired
	private DicDicRepository dicDicRepository;
	@Autowired
	private DicRepository dicRepository;
	private static final Log logger = LogFactory.getLog(DicController.class);

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/dic/saveDicDic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveDicDic(@RequestBody DicDic dicDic) {
		Valid v = new Valid();

		if (dicDic.getName() == null) {
			v.setValid(false);
			v.setMsg("名字不能为空！");
			return v;
		}

		// create new
		if (dicDic.getId() == null || dicDic.getId().equals(0l)) {
			DicDic dbDicDic = dicDicRepository.findByName(dicDic.getName());
			if (dbDicDic != null) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}
		} else {
			DicDic dbDicDic = dicDicRepository.findByName(dicDic.getName());
			if (dbDicDic != null && !dbDicDic.getId().equals(dicDic.getId())) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}

		}
		dicDic = dicDicRepository.save(dicDic);
		v.setValid(true);
		v.setMsg("保存成功，新字典ID：" + dicDic.getId());
		return v;

	}
	
	

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/dic/saveDic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Valid saveDic(@RequestBody Dic dic) {
		Valid v = new Valid();

		if (dic.getCode() == null) {
			v.setValid(false);
			v.setMsg("名字不能为空！");
			return v;
		}
		if (dic.getDicDic() == null||dic.getDicDic().getId()==null) {
			v.setValid(false);
			v.setMsg("字典元数据不能为空！");
			return v;
		}

		// create new
		if (dic.getId() == null || dic.getId().equals(0l)) {
			Dic dbDic = dicRepository.findByCodeAndDicDicId(dic.getCode(), dic.getDicDic().getId());
			if (dbDic != null) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}
		} else {
			Dic dbDic = dicRepository.findByCodeAndDicDicId(dic.getCode(), dic.getDicDic().getId());
			if (dbDic != null && !dbDic.getId().equals(dic.getId())) {
				v.setValid(false);
				v.setMsg("该名字已存在！");
				return v;
			}

		}
		DicDic dicDic = dicDicRepository.findOne(dic.getDicDic().getId());
		dic.setDicDic(dicDic);
		dic = dicRepository.save(dic);
		v.setValid(true);
		v.setMsg("保存成功，新字典ID：" + dic.getId());
		return v;

	}
	
	@RequestMapping(value="/sys/dic/dicDicSelects", method=RequestMethod.GET)
	public List<WSSelectObj> findDicDics(){
			List<WSSelectObj> ws = new ArrayList<WSSelectObj>();
			for(DicDic d: dicDicRepository.findAll())
			{
				WSSelectObj w = new WSSelectObj(d.getId(),d.getName());
				ws.add(w);		
			}
			return ws;
    }

	@RequestMapping(value="/sys/dic/dicTable", method=RequestMethod.POST)
	public WSTableData findDicsByDicDicId(
			@RequestParam("dicDicId") Long dicDicId,
			@RequestParam Integer draw,@RequestParam Integer length) 
	{
		
		Pageable pagaable =  new PageRequest(draw,length);
		Page<Dic> page = dicRepository.findByDicDicId(dicDicId,pagaable);
		List<String[]> lst = new ArrayList<String[]>();
		for(Dic w:page.getContent())
		{
			String[] d = {
					w.getCode(),
					w.getDescr(),
					w.getDicDic().getName(),
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
}
