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
 * 联系我们
 */
@Controller
public class ConnectController {
	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;

	@RequestMapping(MenuConfig.CONNECT_US_PATH)
	public String overview(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.CONNECT_US, MenuConfig.CONNECT_US_PATH);
		CommonTool.getHtmlContent(model, menuServer, htmlContentServer, MenuConfig.CONNECT_US_TYPE);
		CommonTool.setSlideshow(model,menuServer,slideshowServer,MenuConfig.CONNECT_US_TYPE);
		return "connect/connect_us";
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
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.CONNECT_US_TYPE, MenuConfig.CONNECT_US, MenuConfig.CONNECT_US_PATH, curr, currPath);
	}

}
