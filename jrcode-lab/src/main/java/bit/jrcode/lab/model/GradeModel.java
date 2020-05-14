package bit.jrcode.lab.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_dept")
public class GradeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public List<StudentModel> getStudents() {
//		return students;
//	}
//
//	public void setStudents(List<StudentModel> students) {
//		this.students = students;
//	}
}
