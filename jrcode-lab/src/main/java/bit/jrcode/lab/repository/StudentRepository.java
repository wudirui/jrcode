package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.GradeModel;
import bit.jrcode.lab.model.StudentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StudentRepository extends Repository<StudentModel, Long> {

	@Query("select item from StudentModel item")
	List<StudentModel> findStudents();
}
