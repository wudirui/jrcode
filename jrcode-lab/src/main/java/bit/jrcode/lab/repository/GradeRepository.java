package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.GradeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GradeRepository extends Repository<GradeModel, Long> {

	@Query("select item from GradeModel item")
	List<GradeModel> findGrades();

	@Query("select item from GradeModel item where id=?1")
	GradeModel findGradeById(Integer deptId);
}
