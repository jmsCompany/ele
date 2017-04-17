package qingyun.ele;

public enum StatusEnum {

	
	SAVED(1l), COMMITTED(2l), DELETETED(3l);
	 
	private Long statusCode;
 
	private StatusEnum(Long s) {
		statusCode = s;
	}
 
	public Long getStatusCode() {
		return statusCode;
	}
}
