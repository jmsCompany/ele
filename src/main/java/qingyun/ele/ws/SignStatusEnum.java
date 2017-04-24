package qingyun.ele.ws;

public enum SignStatusEnum {

	
	FOR_SIGNED(0l),REFUSED(1l),SIGNED(2l);
	 
	private Long statusCode;
 
	private SignStatusEnum(Long s) {
		statusCode = s;
	}
 
	public Long getStatusCode() {
		return statusCode;
	}
}
