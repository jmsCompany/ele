package qingyun.ele.ws;

public class WSRolePage {

	private Long idPage;
	private String page;
	private Long hasPerm;
	

	public Long getHasPerm() {
		return hasPerm;
	}
	public void setHasPerm(Long hasPerm) {
		this.hasPerm = hasPerm;
	}
	public Long getIdPage() {
		return idPage;
	}
	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
