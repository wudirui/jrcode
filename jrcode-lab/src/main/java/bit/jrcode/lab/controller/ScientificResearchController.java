package bit.jrcode.lab.controller;

import bit.jrcode.lab.config.MenuConfig;
import bit.jrcode.lab.service.HtmlContentServer;
import bit.jrcode.lab.service.MenuServer;
import bit.jrcode.lab.service.SlideshowServer;
import bit.jrcode.lab.utils.CommonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 科学研究
 */
@Controller
public class ScientificResearchController {
	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;

	@RequestMapping(MenuConfig.SCI_RESEARCH_LIGHT_DESIGN_PATH)
	public Object engineResearch(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESEARCH_LIGHT_DESIGN, MenuConfig.SCI_RESEARCH_LIGHT_DESIGN_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESEARCH_LIGHT_DESIGN_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESEARCH_TYPE);
		return "scientific_research/light_design";
	}

	@RequestMapping(MenuConfig.SCI_RESEARCH_PI_LIGHT_CHECK_PATH)
	public Object lightCheck(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESEARCH_PI_LIGHT_CHECK, MenuConfig.SCI_RESEARCH_PI_LIGHT_CHECK_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESEARCH_PI_LIGHT_CHECK_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESEARCH_TYPE);
		return "scientific_research/light_check";
	}

	@RequestMapping(MenuConfig.SCI_RESEARCH_PI_LIGHT_MAKE_PATH)
	public Object lightMake(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESEARCH_PI_LIGHT_MAKE, MenuConfig.SCI_RESEARCH_PI_LIGHT_MAKE_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESEARCH_PI_LIGHT_MAKE_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESEARCH_TYPE);
		return "scientific_research/light_make";
	}

	@RequestMapping(MenuConfig.SCI_RESEARCH_IMAGE_QUALITY_EVALUATION_PATH)
	public Object imageEvaluate(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESEARCH_IMAGE_QUALITY_EVALUATION, MenuConfig.SCI_RESEARCH_IMAGE_QUALITY_EVALUATION_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESEARCH_IMAGE_QUALITY_EVALUATION_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESEARCH_TYPE);

		return "scientific_research/image_evaluate";
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
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.SCI_RESEARCH_TYPE, MenuConfig.SCI_RESEARCH, MenuConfig.SCI_RESEARCH_PATH, curr, currPath);
	}
}
