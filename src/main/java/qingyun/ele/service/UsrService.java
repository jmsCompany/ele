package qingyun.ele.service;

import qingyun.ele.domain.db.Users;

public interface UsrService {
	public boolean register(Users usr);

	public boolean isRegisted(String username);

	public String login(String username, String password);

	public Boolean checkToken(String jmstoken);
}
