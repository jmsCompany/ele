package qingyun.ele.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderTest {

	@Autowired
	private EmailSenderService emailSenderService;
	
	public void testSendEmail() throws Exception {
		String[] to = new String[] { "hongtao.ren@vqingyun.com" };
		String subject = "测试邮件";
		//emailSenderService.sendEmail(to, subject, model, files);
		emailSenderService.sendEmail(to,subject, "hello", null);
		
	}
}
