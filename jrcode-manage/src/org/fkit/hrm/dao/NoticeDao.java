package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.NoticeDynaSqlProvider;
import org.fkit.hrm.domain.Notice;

import static org.fkit.hrm.util.common.HrmConstants.NOTICETABLE;


/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: NoticeMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */
public interface NoticeDao {

	// 动态查询
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectWhitParam")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "menu_name", property = "menuName"),
			@Result(column = "menu_id", property = "menuId"),
			@Result(column = "create_time", property = "createTime", javaType = java.util.Date.class),
			@Result(column = "user_id", property = "user",
					one = @One(select = "org.fkit.hrm.dao.UserDao.selectById",
							fetchType = FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String, Object> params);

	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectNoticeById")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "title", property = "title"),
			@Result(column = "content", property = "content"),
			@Result(column = "menu_id", property = "menuId"),
			@Result(column = "menu_name", property = "menuName"),
			@Result(column = "user_id", property = "user",
					one = @One(select = "org.fkit.hrm.dao.UserDao.selectById",
							fetchType = FetchType.EAGER))
	})
	Notice selectById(Integer id);

	// 根据id删除公告
	@Delete(" delete from " + NOTICETABLE + " where id = #{id} ")
	void deleteById(Integer id);

	// 动态插入公告
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "insertNotice")
	void save(Notice notice);

	// 动态修改公告
	@SelectProvider(type = NoticeDynaSqlProvider.class, method = "updateNotice")
	void update(Notice notice);

}
