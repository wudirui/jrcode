package org.fkit.hrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fkit.hrm.domain.Menu;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: 处理公告请求控制器
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */

@Controller
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	/**
	 * 自动注入UserService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 处理/login请求
	 */
	@RequestMapping(value = "/notice/selectNotice")
	public String selectNotice(Model model, Integer pageIndex,
	                           @ModelAttribute Notice notice) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息 */
		List<Menu> menuList = hrmService.findMenuList();

		List<Notice> notices = hrmService.findNotice(notice, pageModel);
		model.addAttribute("notices", notices);
		model.addAttribute("menuList", menuList);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";

	}

	/**
	 * 处理添加请求
	 *
	 * @param id    要显示的公告id
	 * @param model
	 */
	@RequestMapping(value = "/notice/previewNotice")
	public String previewNotice(Integer id, Model model) {

		Notice notice = hrmService.findNoticeById(id);

		model.addAttribute("notice", notice);
		// 返回
		return "notice/previewNotice";
	}

	/**
	 * 处理删除公告请求
	 *
	 * @param ids 需要删除的id字符串
	 * @param mv
	 */
	@RequestMapping(value = "/notice/removeNotice")
	public ModelAndView removeNotice(String ids, ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			// 根据id删除公告
			hrmService.removeNoticeById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/notice/selectNotice");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 处理添加请求
	 *
	 * @param flag   标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param notice 要添加的公告对象
	 * @param mv
	 */
	@RequestMapping(value = "/notice/addNotice")
	public ModelAndView addNotice(Model model, String flag, @ModelAttribute Notice notice, ModelAndView mv, HttpSession session) {
		if (flag != null && flag.equals("1")) {
			List<Menu> menuList = hrmService.findMenuList();
			User user = new User();
			user.setId(0);
			model.addAttribute("menuList", menuList);
			model.addAttribute("user", user);
			mv.setViewName("notice/showAddNotice");
		} else {
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			List<Menu> menuList = hrmService.findMenuList();
			Map<String, String> map = new HashMap<>();
			for (Menu menu : menuList) {
				map.put(menu.getId() + "", menu.getName());
			}
			Integer menuId = notice.getMenuId();
			String menuName = map.get(menuId + "");
			notice.setMenuName(menuName);
			notice.setUser(user);

			hrmService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}

	/**
	 * 处理添加请求
	 *
	 * @param flag   标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param notice 要添加的公告对象
	 * @param mv
	 */
	@RequestMapping(value = "/notice/updateNotice")
	public ModelAndView updateNotice(String flag, @ModelAttribute Notice notice, ModelAndView mv, HttpSession session) {
		if (flag.equals("1")) {
			Notice target = hrmService.findNoticeById(notice.getId());
			List<Menu> menuList = hrmService.findMenuList();
			logger.info("the notice info is:" + target.toString());
			mv.addObject("menuList", menuList);
			mv.addObject("notice", target);
			mv.setViewName("notice/showUpdateNotice");
		} else {
			List<Menu> menuList = hrmService.findMenuList();
			Map<String, String> map = new HashMap<>();
			for (Menu menu : menuList) {
				map.put(menu.getId() + "", menu.getName());
			}
			Integer menuId = notice.getMenuId();
			String menuName = map.get(menuId + "");
			notice.setMenuName(menuName);

			hrmService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		// 返回
		return mv;
	}

}
