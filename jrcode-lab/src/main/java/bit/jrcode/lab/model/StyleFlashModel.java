package bit.jrcode.lab.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "tb_style_flash")
public class StyleFlashModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String type;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
}
