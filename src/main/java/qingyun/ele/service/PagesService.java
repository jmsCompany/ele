package qingyun.ele.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qingyun.ele.repository.PagesRepository;



@Service
@Transactional
public class PagesService {
	
	@Autowired
	private PagesRepository pagesRepository;
	

}
