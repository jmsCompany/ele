package qingyun.ele.service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	private String from = "system@yashidun.com.cn";

	@Override
	public void sendEmail(final String[] toEmailAddresses, final String subject, final String body,
			final String[] files) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) {
				MimeMessageHelper message;
				try {
					message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setTo(toEmailAddresses);
					message.setFrom(new InternetAddress(from));
					message.setSubject(subject);
					String body = "hello world";
					// System.out.println("body: " + body);
					message.setText(body, true);
					if (files != null) {
						for (String f : files) {
							FileSystemResource file = new FileSystemResource(new File(f));
							message.addAttachment(f, file);
						}
					}

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		this.mailSender.send(preparator);

	}

}
