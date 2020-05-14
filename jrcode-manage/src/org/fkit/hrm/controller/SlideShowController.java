package org.fkit.hrm.controller;

import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Menu;
import org.fkit.hrm.domain.Slideshow;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SlideShowController {
	/**
	 * 自动注入UserService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;


	/**
	 * 处理/login请求
	 */
	@RequestMapping(value = "/slideshow/selectSlideshow")
	public String selectSlideshow(
			Model model, Integer pageIndex,
			@ModelAttribute Menu menu) {

		List<Menu> menuList = hrmService.findParentMenuList();
		User user = new User();
		user.setId(new Integer(0));
		if (menu != null && menu.getId() != null && menu.getId() != 0) {
			user.setId(new Integer(menu.getId()));
		}
		model.addAttribute("menuList", menuList);
		Map<String, String> menuMap = new HashMap<>();
		for (Menu item : menuList) {
			menuMap.put(item.getId() + "", item.getName());
		}
		model.addAttribute("menuMap", menuMap);
		model.addAttribute("user", user);
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}

		List<Slideshow> slideshowes = hrmService.findSlideshow(menu, pageModel);
		for (Slideshow item : slideshowes) {
			String menuName = menuMap.get(item.getMenuId() + "");
			item.setMenuName(menuName);
		}

		model.addAttribute("slideshowes", slideshowes);
		model.addAttribute("pageModel", pageModel);
		return "slideshow/slideshow";

	}

	/**
	 * 处理添加请求
	 */
	@RequestMapping(value = "/slideshow/addSlideshow")
	public ModelAndView addDocument(Model model,
	                                String flag,
	                                @ModelAttribute Slideshow slideshow,
	                                ModelAndView mv,
	                                HttpSession session) throws Exception {
		if (flag.equals("1")) {
			List<Menu> menuList = hrmService.findParentMenuList();
			User user = new User();
			user.setId(0);
			model.addAttribute("menuList", menuList);
			model.addAttribute("user", user);
			mv.setViewName("slideshow/showAddSlideshow");
		} else {
			// 上传文件名
			String fileName = slideshow.getFile().getOriginalFilename();
			String[] suffixArra = fileName.split("\\.");
			String preffix = "data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
			BASE64Encoder base64Encoder = new BASE64Encoder();
			String base64EncoderImg = preffix + base64Encoder.encode(slideshow.getFile().getBytes()).replaceAll("[\\s*\t\n\r]", "");
			slideshow.setImgStr(base64EncoderImg);
			// 设置fileName
			slideshow.setFileName(fileName);
			// 设置关联的User对象
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			slideshow.setUser(user);
			// 插入数据库
			hrmService.saveSlideshow(slideshow);
			// 返回
			mv.setViewName("redirect:/slideshow/selectSlideshow");
		}
		// 返回
		return mv;
	}

	/**
	 * 处理删除文档请求
	 *
	 * @param ids 需要删除的id字符串
	 * @param mv
	 */
	@RequestMapping(value = "/slideshow/removeSlideshow")
	public ModelAndView removeSlideshow(String ids, ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			// 根据id删除文档
			hrmService.removeSlideshowById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/slideshow/selectSlideshow");
		// 返回ModelAndView
		return mv;
	}


}
