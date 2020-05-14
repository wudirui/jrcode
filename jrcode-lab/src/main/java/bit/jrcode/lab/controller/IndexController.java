package bit.jrcode.lab.controller;

import bit.jrcode.lab.config.MenuConfig;
import bit.jrcode.lab.model.HtmlContentModel;
import bit.jrcode.lab.model.MenuParentModel;
import bit.jrcode.lab.model.MenuSubModel;
import bit.jrcode.lab.model.SlideshowModel;
import bit.jrcode.lab.service.HtmlContentServer;
import bit.jrcode.lab.service.MenuServer;
import bit.jrcode.lab.service.SlideshowServer;
import bit.jrcode.lab.utils.CommonTool;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	MenuServer menuServer;

	@Autowired
	HtmlContentServer htmlContentServer;

	@Autowired
	SlideshowServer slideshowServer;


	@RequestMapping()
	public String main() {
		return "redirect:" + MenuConfig.INDEX_PATH;
	}


	@RequestMapping(MenuConfig.INDEX_PATH)
	public String index(Model model) {
		logger.info("home index info");
		setMenuInfo(model, menuServer, MenuConfig.INDEX, MenuConfig.INDEX_PATH);
		model.addAttribute(MenuConfig.INDEX_LAB_STYLE_TYPE, MenuConfig.INDEX_LAB_STYLE);
		model.addAttribute(MenuConfig.INDEX_LAB_NEWS_TYPE, MenuConfig.INDEX_LAB_NEWS);
		List<HtmlContentModel> labStyles = new LinkedList<>();
		List<HtmlContentModel> labNews = new LinkedList<>();

		MenuParentModel indexMenu = menuServer.findMenuByType(MenuConfig.INDEX_TYPE);
		List<MenuSubModel> menuSubs = indexMenu.getMenuSubs();
		logger.info("" + menuSubs.toString());
		for (MenuSubModel menuSub : menuSubs) {
			if (menuSub.getType().equals(MenuConfig.INDEX_LAB_NEWS_TYPE)) {
				labNews.addAll(htmlContentServer.findHtmlContentByMenuId(menuSub.getId()));
				model.addAttribute("labNewsList", labNews);
			}
			if (menuSub.getType().equals(MenuConfig.INDEX_LAB_STYLE_TYPE)) {
				labStyles.addAll(htmlContentServer.findHtmlContentByMenuId(menuSub.getId()));
				model.addAttribute("labStylesList", labStyles);
			}

		}
		model.addAttribute("newsPath", MenuConfig.INDEX_LAB_NEWS_PATH);
		model.addAttribute("stylePath", MenuConfig.INDEX_LAB_STYLE_PATH);

		logger.info(String.valueOf(indexMenu.getId()));
		List<SlideshowModel> slideshowOfHome = slideshowServer.findSlideshowOfHome(indexMenu.getId());
		logger.info("the slideshowOfHome of count is :" + slideshowOfHome.size());
		model.addAttribute("slideshowOfHome", slideshowOfHome);
		return "home/index";
	}


	/**
	 * 搜索
	 */
	@RequestMapping(value = MenuConfig.SEARCH_PATH)
	public String searchPage(Model model, String req) {
		setMenuInfo(model, menuServer, MenuConfig.INDEX, MenuConfig.INDEX_PATH);
		List<HtmlContentModel> contents = null;
		int resultSize = 0;
		if (req != null && !req.equals(""))
			contents = htmlContentServer.findHtmlContent(req);
		if (contents != null && contents.size() != 0) {
			resultSize = contents.size();
			for (HtmlContentModel content : contents) {
				content.setContent(CommonTool.getAwareContent(req, CommonTool.removeHtmlTag(content.getContent())));
			}
		}
		model.addAttribute("resultSize", resultSize);
		model.addAttribute("contents", contents);
		model.addAttribute("req", req);
		return "home/search";
	}

	/**
	 * 搜索详情
	 */
	@RequestMapping(value = MenuConfig.SEARCH_DETAIL_PATH)
	public String searchPage(Model model, @PathVariable Integer id) {
		HtmlContentModel htmlContent = htmlContentServer.findHtmlContentById(id);
		model.addAttribute("htmlContent", htmlContent);
		setMenuInfo(model, menuServer, MenuConfig.INDEX, MenuConfig.INDEX_PATH);
		return "home/search_detail";
	}


	@RequestMapping(MenuConfig.INDEX_LAB_STYLE_DETAIL_PATH)
	public Object getStyle(Model model, @PathVariable Integer id) {
		HtmlContentModel htmlContent = htmlContentServer.findHtmlContentById(id);
		model.addAttribute("htmlContent", htmlContent);
		setMenuInfo(model, menuServer, MenuConfig.INDEX_LAB_STYLE, MenuConfig.INDEX_LAB_STYLE_PATH);
		return "home/detail";
	}

	@RequestMapping(MenuConfig.INDEX_LAB_STYLE_PATH)
	public Object getStyle(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.INDEX_LAB_STYLE, MenuConfig.INDEX_LAB_STYLE_PATH);
		Integer menuId = getMenuId(model, MenuConfig.INDEX_LAB_STYLE_TYPE);
		List<HtmlContentModel> htmlContent = htmlContentServer.findHtmlContentByMenuId(menuId);
		model.addAttribute("htmlContents", htmlContent);
		model.addAttribute("path", MenuConfig.INDEX_LAB_STYLE_PATH);
		return "home/details";
	}


	@RequestMapping(MenuConfig.INDEX_LAB_NEWS_DETAIL_PATH)
	public Object getNews(Model model, @PathVariable Integer id) {
		HtmlContentModel htmlContent = htmlContentServer.findHtmlContentById(id);
		model.addAttribute("htmlContent", htmlContent);
		setMenuInfo(model, menuServer, MenuConfig.INDEX_LAB_NEWS, MenuConfig.INDEX_LAB_NEWS_PATH);
		return "home/detail";
	}

	@RequestMapping(MenuConfig.INDEX_LAB_NEWS_PATH)
	public Object getNews(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.INDEX_LAB_NEWS, MenuConfig.INDEX_LAB_NEWS_PATH);
		Integer menuId = getMenuId(model, MenuConfig.INDEX_LAB_NEWS_TYPE);
		List<HtmlContentModel> htmlContent = htmlContentServer.findHtmlContentByMenuId(menuId);
		model.addAttribute("path", MenuConfig.INDEX_LAB_NEWS_PATH);
		model.addAttribute("htmlContents", htmlContent);
		return "home/details";
	}

	private Integer getMenuId(Model model, String type) {
		List<MenuSubModel> menuSubs = (List<MenuSubModel>) model.getAttribute("menuSubs");
		Integer id = null;
		for (MenuSubModel menuSub : menuSubs) {
			if (menuSub.getType().equals(type)) {
				id = menuSub.getId();
				break;
			}
		}
		return id;
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
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.INDEX_TYPE, MenuConfig.INDEX, MenuConfig.INDEX_PATH, curr, currPath);
	}
}
