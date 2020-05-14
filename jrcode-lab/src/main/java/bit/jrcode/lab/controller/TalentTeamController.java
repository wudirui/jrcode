package bit.jrcode.lab.controller;

import bit.jrcode.lab.config.MenuConfig;
import bit.jrcode.lab.model.EducationModel;
import bit.jrcode.lab.model.GradeModel;
import bit.jrcode.lab.model.StudentModel;
import bit.jrcode.lab.service.HtmlContentServer;
import bit.jrcode.lab.service.MenuServer;
import bit.jrcode.lab.service.SlideshowServer;
import bit.jrcode.lab.service.TalentTeamServer;
import bit.jrcode.lab.utils.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 人才队伍
 */
@Controller
public class TalentTeamController {

	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;

	@Autowired
	TalentTeamServer talentTeamServer;

	@RequestMapping(MenuConfig.TALENT_TEAM_TEACHER_PATH)
	public Object talentTeacher(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.TALENT_TEAM_TEACHER, MenuConfig.TALENT_TEAM_TEACHER_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.TALENT_TEAM_TEACHER_TYPE);
		CommonTool.setSlideshow(model, menuServer, slideshowServer, MenuConfig.TALENT_TEAM_TYPE);
		return "talent_team/teacher";
	}

	@RequestMapping(MenuConfig.TALENT_TEAM_RESEARCHER_PATH)
	public Object talentResearcher(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.TALENT_TEAM_RESEARCHER, MenuConfig.TALENT_TEAM_RESEARCHER_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.TALENT_TEAM_RESEARCHER_TYPE);
		CommonTool.setSlideshow(model, menuServer, slideshowServer, MenuConfig.TALENT_TEAM_TYPE);

		List<StudentModel> students = talentTeamServer.findStudents();
		for (StudentModel student : students) {
			GradeModel grade = talentTeamServer.findGradeById(student.getDeptId());//按照年级查找
			EducationModel education = talentTeamServer.findEducationById(student.getJobId());//按照年级查找
			student.setJobName(education.getName());
			student.setDeptName(grade.getName());
		}
		model.addAttribute("students", students);
		return "talent_team/researcher";
	}

	/**
	 * 设置菜单
	 *
	 * @param model      model
	 * @param menuServer 菜单服务
	 * @param curr       当前菜单名称
	 * @param currPath   当前菜单路径
	 */
	public static void setMenuInfo(Model model, MenuServer menuServer, String curr, String currPath) {
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.TALENT_TEAM_TYPE, MenuConfig.TALENT_TEAM, MenuConfig.TALENT_TEAM_PATH, curr, currPath);
	}
}
