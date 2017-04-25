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
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.repository.DicDicRepository;
import qingyun.ele.repository.DicRepository;
import qingyun.ele.repository.LocationRepository;
import qingyun.ele.repository.StepsRepository;
import qingyun.ele.repository.SubLocationRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.service.LocationService;
import qingyun.ele.service.UsrService;
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

	@RequestMapping(value="/sys/location/allLocationSelects", method=RequestMethod.GET)
	public List<WSSelectObj> locationSelects1(){
			return locationService.getLocations(null);
    }
	
	@RequestMapping(value="/sys/location/subLocationSelects", method=RequestMethod.GET)
	public List<WSSelectObj> subLocationSelects(@RequestParam("locationId") Long locationId){
			return locationService.getSublocationsByLocation(locationId, null);
    }
	
	@RequestMapping(value="/sys/location/subSubLocationSelects", method=RequestMethod.GET)
	public List<WSSelectObj> subSubLocationSelects(@RequestParam("subLocationId") Long subLocationId){
			return locationService.getSubSublocations(subLocationId, null);
    }
	
	@RequestMapping(value="/sys/location/locationSelects", method=RequestMethod.GET)
	public List<WSSelectObj> locationSelects(){
			return locationService.getSubSublocations();
    }
	
	@Transactional(readOnly = false)
	@RequestMapping(value = "/sys/location/enableSubSubLocation", method = RequestMethod.GET)
	public Valid deleteDic(@RequestParam("subSubLocationId") Long subSubLocationId) {
		
		Valid v = new Valid();
		SubSubLocation subSubLocation = subSubLocationRepository.findOne(subSubLocationId);
		if(subSubLocation==null)
		{
			v.setValid(false);
			v.setMsg("不能找到此数据 ID： " +subSubLocationId);
			return v;
		}
		subSubLocation.setEnabled(1l);
		subSubLocationRepository.save(subSubLocation);
		v.setValid(true);
		return v;

	}
	
}
