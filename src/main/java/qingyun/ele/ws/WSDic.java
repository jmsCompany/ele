package qingyun.ele.ws;

public class WSDic implements java.io.Serializable {

	private Long id;
	private Long dicDicId;
	private String code;
	private String descr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDicDicId() {
		return dicDicId;
	}

	public void setDicDicId(Long dicDicId) {
		this.dicDicId = dicDicId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
