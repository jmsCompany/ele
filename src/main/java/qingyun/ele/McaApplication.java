package qingyun.ele;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import qingyun.ele.domain.db.Users;
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
	
	}
	

}
