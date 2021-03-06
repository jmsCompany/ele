package qingyun.ele.domain.db;
// Generated 2017-4-17 14:45:18 by Hibernate Tools 3.2.2.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pages generated by hbm2java
 */
@Entity
@Table(name = "pages", catalog = "ele")
public class Pages implements java.io.Serializable {

	private Long id;
	private String name;
	private String descr;
	private String menuName;
	private String groups;
	private String url;
	private Long seq;
	private Set<RolePages> rolePageses = new HashSet<RolePages>(0);

	public Pages() {
	}

	public Pages(Long id) {
		this.id = id;
	}

	public Pages(Long id, String name, String descr, String menuName, Set<RolePages> rolePageses) {
		this.id = id;
		this.name = name;
		this.descr = descr;
		this.menuName = menuName;
		this.rolePageses = rolePageses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "descr", length = 64)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "menu_name", length = 64)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pages")
	public Set<RolePages> getRolePageses() {
		return this.rolePageses;
	}

	public void setRolePageses(Set<RolePages> rolePageses) {
		this.rolePageses = rolePageses;
	}

	@Column(name = "groups", length = 64)
	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	@Column(name = "url", length = 64)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "seq")
	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}
}
