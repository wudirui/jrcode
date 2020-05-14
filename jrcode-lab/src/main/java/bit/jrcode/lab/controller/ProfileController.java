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
 * 概况
 */
@Controller
public class ProfileController {

	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;

	@RequestMapping(MenuConfig.PROFILE_OVERVIEW_PATH)
	public String overview(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.PROFILE_OVERVIEW, MenuConfig.PROFILE_OVERVIEW_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.PROFILE_OVERVIEW_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.PROFILE_TYPE);
		return "profile/overview";
	}


	@RequestMapping(MenuConfig.PROFILE_COOPERATE_PATH)
	public String cooperate(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.PROFILE_COOPERATE, MenuConfig.PROFILE_COOPERATE_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.PROFILE_COOPERATE_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.PROFILE_TYPE);
		return "profile/cooperate";
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
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.PROFILE_TYPE, MenuConfig.PROFILE, MenuConfig.PROFILE_PATH, curr, currPath);
	}


}
