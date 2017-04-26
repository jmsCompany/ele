package qingyun.ele.service;

public interface EmailSenderService {

	public void sendEmail(String[] toEmailAddresses,String subject, String body
		,String[] files );

}
