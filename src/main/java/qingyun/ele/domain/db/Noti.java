package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Noti generated by hbm2java
 */
@Entity
@Table(name = "noti", catalog = "ele")
public class Noti implements java.io.Serializable {

	private Long id;
	private Steps steps;
	private String start;
	private String end;
	private String delay;
	private String delayMore;

	public Noti() {
	}

	public Noti(Long id) {
		this.id = id;
	}

	public Noti(Long id, Steps steps, String start, String end, String delay, String delayMore) {
		this.id = id;
		this.steps = steps;
		this.start = start;
		this.end = end;
		this.delay = delay;
		this.delayMore = delayMore;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_step")
	public Steps getSteps() {
		return this.steps;
	}

	public void setSteps(Steps steps) {
		this.steps = steps;
	}

	@Column(name = "start", length = 64)
	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	@Column(name = "end", length = 64)
	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Column(name = "delay", length = 64)
	public String getDelay() {
		return this.delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	@Column(name = "delay_more", length = 64)
	public String getDelayMore() {
		return this.delayMore;
	}

	public void setDelayMore(String delayMore) {
		this.delayMore = delayMore;
	}

}
