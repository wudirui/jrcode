package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Notice;

import static org.fkit.hrm.util.common.HrmConstants.NOTICETABLE;


/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: 公告动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */
public class NoticeDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(NOTICETABLE);
				if (params.get("notice") != null) {
					Notice notice = (Notice) params.get("notice");
					if (notice.getTitle() != null && !notice.getTitle().equals("")) {
						WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
					}
					if (notice.getContent() != null && !notice.getContent().equals("")) {
						WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
					}

					if (notice.getMenuId() != null) {
						WHERE("  menu_id = #{notice.menuId} ");
					}

				}
			}
		}.toString();

		if (params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}

		return sql;
	}

	// 通过id查询公告
	public String selectNoticeById(Integer id) {

		String sql = new SQL() {
			{
				SELECT("*");
				FROM(NOTICETABLE);
				Notice notice = new Notice();
				notice.setId(id);
				WHERE("  id = #{notice.id} ");
			}
		}.toString();
		return sql;
	}


	// 动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(NOTICETABLE);
				if (params.get("notice") != null) {
					Notice notice = (Notice) params.get("notice");
					if (notice.getTitle() != null && !notice.getTitle().equals("")) {
						WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
					}
					if (notice.getContent() != null && !notice.getContent().equals("")) {
						WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
					}
				}
			}
		}.toString();
	}

	// 动态插入
	public String insertNotice(Notice notice) {

		return new SQL() {
			{
				INSERT_INTO(NOTICETABLE);
				if (notice.getTitle() != null && !notice.getTitle().equals("")) {
					VALUES("title", "#{title}");
				}
				if (notice.getContent() != null && !notice.getContent().equals("")) {
					VALUES("content", "#{content}");
				}
				if (notice.getUser() != null && notice.getUser().getId() != null) {
					VALUES("user_id", "#{user.id}");
				}
				if (notice.getMenuId() != null && notice.getMenuId() != 0) {
					VALUES("menu_id", "#{menuId}");
				}
				if (notice.getMenuName() != null) {
					VALUES("menu_name", "#{menuName}");
				}
			}
		}.toString();
	}

	// 动态更新
	public String updateNotice(Notice notice) {

		return new SQL() {
			{
				UPDATE(NOTICETABLE);
				if (notice.getTitle() != null && !notice.getTitle().equals("")) {
					SET(" title = #{title} ");
				}
				if (notice.getContent() != null && !notice.getContent().equals("")) {
					SET(" content = #{content} ");
				}
				if (notice.getUser() != null && notice.getUser().getId() != null) {
					SET(" user_id = #{user.id} ");
				}
				if (notice.getMenuId() != null && notice.getMenuId() != 0) {
					SET(" menu_id = #{menuId} ");
				}
				if (notice.getMenuName() != null) {
					SET(" menu_name = #{menuName} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
