package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.EducationModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EducationRepository extends Repository<EducationModel, Long> {


	@Query("select item from EducationModel item")
	List<EducationModel> findEducations();

	@Query("select item from EducationModel item where id=?1")
	EducationModel findEducationById(Integer deptId);
}
