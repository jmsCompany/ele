package qingyun.ele.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.*;
import qingyun.ele.repository.*;
import qingyun.ele.service.EmailSenderService;
import qingyun.ele.service.UsrService;
import qingyun.ele.ws.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@Transactional(readOnly = true)
public class PersonalpowerStationController {

	@Autowired
	private UsrService usrService;
	@Autowired
	private SubSubLocationRepository subSubLocationRepository;

	
	//个人电站信息
	//查询年份数据
	@RequestMapping(value = "/persionalpowerstation/byyear", method = RequestMethod.GET)
	public Object getpersionalpowerstationbyyear(@RequestParam String  year) {
       String temp="[{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"0\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":302.78,\"Year\":\"2017\",\"Mouth\":\"1\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":1017.28,\"Year\":\"2017\",\"Mouth\":\"2\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":954.88,\"Year\":\"2017\",\"Mouth\":\"3\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":961.12,\"Year\":\"2017\",\"Mouth\":\"4\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":877.47,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"6\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"7\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"8\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"9\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"10\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"11\",\"Day\":\"1\",\"Hour\":\"0\",\"Min\":\"0\",\"Sec\":\"0\"}]";
		return temp;
	}
	//查询月份数据
	@RequestMapping(value = "/persionalpowerstation/bymonth", method = RequestMethod.GET)
	public Object getpersionalpowerstationbymonth(@RequestParam String  year) {
		String temp="[{\"y\":32.01,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"1\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":50.34,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"2\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":45.11,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"3\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":40.7,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"4\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":10.51,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"5\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":16.65,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"6\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":38.3,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"7\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":37.72,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"8\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":37.85,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"9\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":2.96,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"10\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":30.05,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"11\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":29.38,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"12\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":28.98,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"13\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":39.89,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"14\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":38.57,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"15\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":46.9,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"16\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":48.27,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"17\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":41.04,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"18\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":15.57,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"19\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":20.06,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"20\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":25.2,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"21\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":22.11,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"22\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":14.87,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"23\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":23.5,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"24\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":16.1,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"25\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":30.18,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"26\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":40.05,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"27\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":24.8,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"28\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":29.8,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"29\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"},{\"y\":0,\"Year\":\"2017\",\"Mouth\":\"5\",\"Day\":\"30\",\"Hour\":\"1\",\"Min\":\"0\",\"Sec\":\"0\"}]";
		return temp;
	}
	

}
