package bit.jrcode.lab.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_menu_sub")
public class MenuSubModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String name;

	@Column
	private String url;

	@Column
	private String type;

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

	@Override
	public String toString() {
		return "MenuSubModel{" +
				"id=" + id +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
