package qingyun.ele;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import qingyun.ele.domain.db.Users;
import qingyun.ele.service.LocationService;
import qingyun.ele.service.UsrService;




@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class McaApplication {
	

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(McaApplication.class, args);
		
//		UsrService us =	ctx.getBean(UsrService.class);
//		Users u = new Users();
//		u.setUsername("admin");
//		u.setPassword("admin");
//		u.setEmail("ren@ecust.edu.cn");
//		u.setEnabled(1l);
//		us.register(u);
		
//		Resource locationRes = ctx.getResource("classpath:data/province.csv");
//		
//		LocationService ls = ctx.getBean(LocationService.class);
//		try {
//			ls.loadLocationsFromCSV(locationRes.getInputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//	Resource sublocationRes = ctx.getResource("classpath:data/city.csv");
//		
//		LocationService ls = ctx.getBean(LocationService.class);
//		try {
//			ls.loadSubLocationsFromCSV(sublocationRes.getInputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
//	
	
//	Resource subsublocationRes = ctx.getResource("classpath:data/district.csv");
//	
//	LocationService ls = ctx.getBean(LocationService.class);
//	try {
//		ls.loadSubSubFromCSV(subsublocationRes.getInputStream());
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

}

}
