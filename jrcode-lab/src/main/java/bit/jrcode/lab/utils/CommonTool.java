package bit.jrcode.lab.utils;


import bit.jrcode.lab.config.MenuConfig;
import bit.jrcode.lab.model.HtmlContentModel;
import bit.jrcode.lab.model.MenuParentModel;
import bit.jrcode.lab.model.MenuSubModel;
import bit.jrcode.lab.model.SlideshowModel;
import bit.jrcode.lab.service.HtmlContentServer;
import bit.jrcode.lab.service.MenuServer;
import bit.jrcode.lab.service.SlideshowServer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonTool {

	private static Logger logger = LoggerFactory.getLogger(CommonTool.class);

	/**
	 * 设置菜单
	 */
	public static void setMenuInfo(Model model, MenuServer menuServer, String parentType, String parentName,
	                               String parentTypePath, String subType, String subTypePath) {
		List<MenuParentModel> menus = menuServer.findAllMenu();
		Iterator<MenuParentModel> iterator = menus.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getType().equals(MenuConfig.INDEX_TYPE))
				iterator.remove();
		}
		model.addAttribute("menus", menus);
		model.addAttribute("pMenuName", parentName);
		//子菜单
		List<MenuSubModel> menuSubs = menuServer.findMenuByType(parentType).getMenuSubs();
		model.addAttribute("menuSubs", menuSubs);

		//当前位置
		Map<String, String> positionUrlMap = new LinkedHashMap<>();
		positionUrlMap.put(MenuConfig.INDEX, MenuConfig.INDEX_PATH);
		positionUrlMap.put(parentName, parentTypePath);
		positionUrlMap.put(subType, subTypePath);
		logger.info("当前位置：" + new Gson().toJson(positionUrlMap));
		model.addAttribute("currPositions", positionUrlMap);
	}

	/**
	 * 获取每个子菜单下面的页面内容
	 */
	public static void getHtmlContent(Model model, MenuServer menuServer, HtmlContentServer htmlContentServer, String type) {
		List<MenuSubModel> menuSubModelList = menuServer.findSubMenuByType(type);
		MenuSubModel menuSubModel = menuSubModelList.get(0);
		List<HtmlContentModel> htmlContent = htmlContentServer.findHtmlContentByMenuId(menuSubModel.getId());

		if (htmlContent != null && htmlContent.size() != 0) {
			HtmlContentModel htmlContentModel = htmlContent.get(0);
			if (htmlContentModel != null) {
				model.addAttribute("htmlContent", htmlContentModel.getContent());
			}
		}
	}

	/**
	 * 设置轮播图
	 */
	public static void setSlideshow(Model model, MenuServer menuServer, SlideshowServer slideshowServer, String type) {
		MenuParentModel indexMenu = menuServer.findMenuByType(type);
		SlideshowModel slideshowModel = new SlideshowModel();

		if (indexMenu != null) {
			List<SlideshowModel> slideshowList = slideshowServer.findSlideshowOfHome(indexMenu.getId());
			for (int i = 0; i < slideshowList.size(); i++) {
				slideshowModel = slideshowList.get(0);
				break;
			}
		}
		model.addAttribute("slideshow", slideshowModel);
	}

	/**
	 * 删除Html标签
	 */
	public static String removeHtmlTag(String htmlStr) {
		//定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		//定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
		String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		//定义HTML标签的正则表达式
		String regEx_html = "<[^>]+>";
		//定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		String regEx_special = "\\&[a-zA-Z]{1,10};";

		//1.过滤script标签
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");
		//2.过滤style标签
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");
		//3.过滤html标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");
		//4.过滤特殊标签
		Pattern p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
		Matcher m_special = p_special.matcher(htmlStr);
		htmlStr = m_special.replaceAll("");

		return htmlStr;
	}

	public static String getAwareContent(String req, String content) {

		String[] split = content.split(req);
		return content;
	}

}
