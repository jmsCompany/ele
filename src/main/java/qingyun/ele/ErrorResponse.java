package qingyun.ele;

import java.io.Serializable;
import java.util.Date;

import qingyun.ele.ws.Valid;

public class ErrorResponse extends Valid implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String path;
    private String eleError;
    private String status;
    
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEleError() {
		return eleError;
	}
	public void setEleError(String eleError) {
		this.eleError = eleError;
	}
}
