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
 * 科研成果
 */
@Controller
public class ScientificResultController {

	@Autowired
	MenuServer menuServer;
	@Autowired
	HtmlContentServer htmlContentServer;
	@Autowired
	SlideshowServer slideshowServer;

	@RequestMapping(MenuConfig.SCI_RESULT_ER_PATH)
	public Object engineResearch(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESULT_ER, MenuConfig.SCI_RESULT_ER_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESULT_ER_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESULT_TYPE);
		return "scientific_result/engine_research";
	}

	@RequestMapping(MenuConfig.SCI_RESULT_PAPER_PUBLISH_PATH)
	public Object paperPublish(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESULT_PAPER_PUBLISH, MenuConfig.SCI_RESULT_PAPER_PUBLISH_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESULT_PAPER_PUBLISH_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESULT_TYPE);
		return "scientific_result/paper_publish";
	}

	@RequestMapping(MenuConfig.SCI_RESULT_PATENT_PUBLISH_PATH)
	public Object patentPublish(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESULT_PATENT_PUBLISH, MenuConfig.SCI_RESULT_PATENT_PUBLISH_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESULT_PATENT_PUBLISH_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESULT_TYPE);
		return "scientific_result/patent_publish";
	}

	@RequestMapping(MenuConfig.SCI_RESULT_HONOR_QUALITY_PATH)
	public Object honorQuality(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.SCI_RESULT_HONOR_QUALITY, MenuConfig.SCI_RESULT_HONOR_QUALITY_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.SCI_RESULT_HONOR_QUALITY_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.SCI_RESULT_TYPE);
		return "scientific_result/honor_quality";
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
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.SCI_RESULT_TYPE, MenuConfig.SCI_RESULT, MenuConfig.SCI_RESULT_PATH, curr, currPath);
	}
}
