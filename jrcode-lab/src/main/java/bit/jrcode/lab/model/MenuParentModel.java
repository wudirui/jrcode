package bit.jrcode.lab.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_menu_parent")
public class MenuParentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String name;

	@Column
	private String url;

	@Column(unique = true)
	private String type;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_menu_parent_sub", joinColumns = {@JoinColumn(name = "parent_id")}
			, inverseJoinColumns = {@JoinColumn(name = "sub_id")})
	private List<MenuSubModel> menuSubs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MenuSubModel> getMenuSubs() {
		return menuSubs;
	}

	public void setMenuSubs(List<MenuSubModel> menuSubs) {
		this.menuSubs = menuSubs;
	}
}
