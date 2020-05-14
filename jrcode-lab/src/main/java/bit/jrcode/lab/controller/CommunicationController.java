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
 * 国际交流
 */
@Controller
public class CommunicationController {
	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;

	@RequestMapping(MenuConfig.NATION_EXCHANGE_STATUS_PATH)
	public String exchangeStatus(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.NATION_EXCHANGE_STATUS, MenuConfig.NATION_EXCHANGE_STATUS_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.NATION_EXCHANGE_STATUS_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.NATION_EXCHANGE_TYPE);
		return "nation_exchange/status";
	}


	@RequestMapping(MenuConfig.NATION_EXCHANGE_COOPERATE_PRO_PATH)
	public String cooperatePro(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.NATION_EXCHANGE_COOPERATE_PRO, MenuConfig.NATION_EXCHANGE_COOPERATE_PRO_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.NATION_EXCHANGE_COOPERATE_PRO_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.NATION_EXCHANGE_TYPE);
		return "nation_exchange/pro";
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
