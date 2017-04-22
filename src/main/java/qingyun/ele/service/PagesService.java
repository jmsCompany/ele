package qingyun.ele.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvReader;

import qingyun.ele.domain.db.Location;
import qingyun.ele.domain.db.SubLocation;
import qingyun.ele.domain.db.SubSubLocation;
import qingyun.ele.repository.LocationRepository;
import qingyun.ele.repository.PagesRepository;
import qingyun.ele.repository.SubLocationRepository;
import qingyun.ele.repository.SubSubLocationRepository;
import qingyun.ele.ws.WSSelectObj;



@Service
@Transactional
public class PagesService {
	
	@Autowired
	private PagesRepository pagesRepository;
	

}
