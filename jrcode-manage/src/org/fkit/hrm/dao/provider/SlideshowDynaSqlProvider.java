package org.fkit.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Menu;
import org.fkit.hrm.domain.Slideshow;

import java.util.Map;

import static org.fkit.hrm.util.common.HrmConstants.SLIDESHOW;

public class SlideshowDynaSqlProvider {

	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(SLIDESHOW);
				if (params.get("menu") != null) {
					Menu menu = (Menu) params.get("menu");
					if (menu.getId() != null) {
						WHERE("  menu_id = #{menu.id} ");
					}

				}
				ORDER_BY("menu_id");
				ORDER_BY("sort");
			}
		}.toString();
		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}

	// 动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(SLIDESHOW);
				if (params.get("menu") != null) {
					Menu menu = (Menu) params.get("menu");
					if (menu.getId() != null) {
						WHERE("  menu_id = #{menu.id} ");
					}
				}
			}
		}.toString();
	}


	// 动态插入
	public String insertSlideshow(Slideshow slideshow) {
		return new SQL() {
			{
				INSERT_INTO(SLIDESHOW);
				if (slideshow.getMenuId() != null && !slideshow.getMenuId().equals("")) {
					VALUES("menu_id", "#{menuId}");
				}
				if (slideshow.getImgStr() != null && !slideshow.getImgStr().equals("")) {
					VALUES("img_str", "#{imgStr}");
				}
				if (slideshow.getFileName() != null && !slideshow.getFileName().equals("")) {
					VALUES("file_name", "#{fileName}");
				}
				VALUES("sort", "#{sort}");
				if (slideshow.getUser() != null && slideshow.getUser().getId() != null) {
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
}
