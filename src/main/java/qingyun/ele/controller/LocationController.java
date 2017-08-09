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
import qingyun.ele.LocationCodeModel;
import qingyun.ele.domain.db.Customer;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.repository.LocationRepository;
import qingyun.ele.repository.SubLocationRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.service.LocationService;
import qingyun.ele.ws.Valid;
import qingyun.ele.ws.WSSelectObj;
import qingyun.ele.ws.WSTableData;

@RestController
@Transactional(readOnly = true)
public class LocationController {

	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private SubLocationRepository subLocationRepository;
	@Autowired
	private SubSubLocationRepository subSubLocationRepository;

	private static final Log logger = LogFactory.getLog(LocationController.class);

	@RequestMapping(value = "/sys/location/allLocationSelects", method = RequestMethod.GET)
	public List<WSSelectObj> allLocationSelects() {
		return locationService.getLocations(null);
	}

	@RequestMapping(value = "/sys/location/subLocationSelects", method = RequestMethod.GET)
	public List<WSSelectObj> subLocationSelects(@RequestParam("locationId") Long locationId) {
		return locationService.getSublocationsByLocation(locationId, null);
	}

	@RequestMapping(value = "/sys/location/subSubLocationSelects", method = RequestMethod.GET)
	public List<WSSelectObj> subSubLocationSelects(@RequestParam("subLocationId") Long subLocationId) {
		return locationService.getSubSublocations(subLocationId, null);
	}

	@RequestMapping(value = "/sys/location/locationSelects", method = RequestMethod.GET)
	public List<WSSelectObj> locationSelects() {
		return locationService.getSubSublocations();
	}

	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/location/changeStatus", method = RequestMethod.GET)
	public Valid enableSubSubLocation(@RequestParam("subSubLocationId") Long subSubLocationId,
			@RequestParam("enabled") Long enabled,@RequestParam("code") String code) {
		
		System.out.println("save location: enabled: " + enabled + ", code: " + code);

		Valid v = new Valid();
		SubSubLocation subSubLocation = subSubLocationRepository.findOne(subSubLocationId);
		if (subSubLocation == null) {
			v.setValid(false);
			v.setMsg("不能找到此数据 ID： " + subSubLocationId);
			return v;
		}
		if (enabled == null || (!enabled.equals(0l) && !enabled.equals(1l))) {
			v.setValid(false);
			v.setMsg("状态只能为 0 或 1 ");
			return v;
		}
		if (enabled.equals(0l)) {
			
			for(Customer c : subSubLocation.getCustomers())
			{
				if(c.getDeleted()!=null&&c.getDeleted().equals(0l))
				{
					v.setValid(false);
					v.setMsg("该区域被使用，不能被删除");
					return v;
				}
			}

		}
		if(code==null||code.isEmpty())
		{
			v.setValid(false);
			v.setMsg("区域编码不能为空");
			return v;
		}
		List<SubSubLocation> subSubLocations = subSubLocationRepository.findByCode(code);
		if (subSubLocations!=null&&subSubLocations.size()>0){
			v.setValid(false);
			v.setMsg("区域编码已存在");
			return v;
		}
		
		
		if(enabled.equals(0l))
		{
			subSubLocation.setCode(null);
			System.out.println("code is null");
		}
		else
		{
			subSubLocation.setCode(code);
		}
		subSubLocation.setEnabled(enabled);
		subSubLocationRepository.save(subSubLocation);
		v.setValid(true);
		return v;

	}
	
	
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "sys/location/deleteLoc", method = RequestMethod.GET)
	public Valid enableSubSubLocation(@RequestParam("subSubLocationId") Long subSubLocationId,
			@RequestParam("enabled") Long enabled) {

		Valid v = new Valid();
		SubSubLocation subSubLocation = subSubLocationRepository.findOne(subSubLocationId);
		if (subSubLocation == null) {
			v.setValid(false);
			v.setMsg("不能找到此数据 ID： " + subSubLocationId);
			return v;
		}
		if (enabled == null || (!enabled.equals(0l) && !enabled.equals(1l))) {
			v.setValid(false);
			v.setMsg("状态只能为 0 或 1 ");
			return v;
		}
		if (enabled.equals(0l)) {
			
			for(Customer c : subSubLocation.getCustomers())
			{
				if(c.getDeleted()!=null&&c.getDeleted().equals(0l))
				{
					v.setValid(false);
					v.setMsg("该区域被使用，不能被删除");
					return v;
				}
			}
		}
		subSubLocation.setEnabled(enabled);
		subSubLocation.setCode(null);
		subSubLocationRepository.save(subSubLocation);
		v.setValid(true);
		return v;

	}
	
	
	

	@RequestMapping(value = "/sys/location/locationTable", method = RequestMethod.POST)
	public WSTableData logsTable(@RequestParam Integer draw, @RequestParam Integer start,
			@RequestParam Integer length) {

		int page_num = (start.intValue() / length.intValue()) + 1;
		// logger.debug("page_num: " + page_num);
		Pageable pageable = new PageRequest(page_num - 1, length);
		Page<SubSubLocation> loc = subSubLocationRepository.findByEnabled(1l, pageable);
		List<String[]> lst = new ArrayList<String[]>();
		for (SubSubLocation s : loc.getContent()) {
			String code ="";
			if(s.getCode()!=null)
			{
				code = s.getCode();
			}
			String[] d = { "" + s.getId(),
					s.getSubLocation().getLocation().getName() + "," + s.getSubLocation().getName() + "," + s.getName(),code,
					"" + s.getId() };
			lst.add(d);
		}

		WSTableData t = new WSTableData();
		t.setDraw(draw);
		t.setRecordsTotal((int) loc.getTotalElements());
		t.setRecordsFiltered((int) loc.getTotalElements());
		t.setData(lst);
		return t;
	}

	/**
	 * 根据subSubLocationId查询对应的区域码
	 * @param subSubLocationId
	 * @return
     */
	@RequestMapping(value = "/sys/location/findLocationCode",method = RequestMethod.GET)
	public LocationCodeModel getSubSubLocationCodeById(@RequestParam Long subSubLocationId){
		SubSubLocation subSubLocation = subSubLocationRepository.findOne(subSubLocationId);
		LocationCodeModel model=new LocationCodeModel();
		model.setId(subSubLocation.getId());
		model.setCode(subSubLocation.getCode());
		return model;
	}

}
