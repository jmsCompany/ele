package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "code_num", catalog = "ele")
public class CodeNum implements java.io.Serializable {

	private Long id;
	private String prefix;
	private String descr;
	private Long curr_val;

	public CodeNum() {
	}

	public CodeNum(Long id) {
		this.id = id;
	}


	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "prefix", length = 64)
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "descr", length = 64)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Column(name = "curr_val")
	public Long getCurr_val() {
		return curr_val;
	}

	public void setCurr_val(Long curr_val) {
		this.curr_val = curr_val;
	}

}