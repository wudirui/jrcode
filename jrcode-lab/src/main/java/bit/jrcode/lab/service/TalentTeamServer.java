package bit.jrcode.lab.service;

import bit.jrcode.lab.model.EducationModel;
import bit.jrcode.lab.model.GradeModel;
import bit.jrcode.lab.model.StudentModel;
import bit.jrcode.lab.repository.EducationRepository;
import bit.jrcode.lab.repository.GradeRepository;
import bit.jrcode.lab.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentTeamServer {
	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EducationRepository educationRepository;

	public List<GradeModel> findGrades() {
		return gradeRepository.findGrades();
	}

	public List<StudentModel> findStudents() {
		return studentRepository.findStudents();
	}

	public List<EducationModel> findEducations() {
		return educationRepository.findEducations();
	}

	public GradeModel findGradeById(Integer deptId) {
		return gradeRepository.findGradeById(deptId);
	}

	public EducationModel findEducationById(Integer id) {
		return educationRepository.findEducationById(id);
	}
}
