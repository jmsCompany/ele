package qingyun.ele.ws;

public class WSUserRole implements java.io.Serializable {


	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	 private Long isSelected;
	 private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Long isSelected) {
		this.isSelected = isSelected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
