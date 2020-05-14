package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.SlideshowDynaSqlProvider;
import org.fkit.hrm.domain.Slideshow;

import java.util.List;
import java.util.Map;

import static org.fkit.hrm.util.common.HrmConstants.SLIDESHOW;

public interface SlideshowDao {
	@SelectProvider(type = SlideshowDynaSqlProvider.class, method = "insertSlideshow")
	void saveSlideshow(Slideshow slideshow);


	// 动态查询
	@SelectProvider(type = SlideshowDynaSqlProvider.class, method = "selectWhitParam")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "file_name", property = "fileName"),
			@Result(column = "img_str", property = "imgStr"),
			@Result(column = "menu_id", property = "menuId"),
			@Result(column = "create_time", property = "createTime", javaType = java.util.Date.class),
			@Result(column = "user_id", property = "user",
					one = @One(select = "org.fkit.hrm.dao.UserDao.selectById",
							fetchType = FetchType.EAGER))
	})
	List<Slideshow> findSlideshow(Map<String, Object> params);

	@SelectProvider(type = SlideshowDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	@Select("select * from " + SLIDESHOW + " where ID = #{id}")
	Slideshow selectById(Integer id);

	@Delete(" delete from " + SLIDESHOW + " where id = #{id} ")
	void deleteById(Integer i);

	@Update(" update " + SLIDESHOW + " set sort = #{sort} where id = #{id} and menu_id = #{menuId} ")
	void sortSlideshow(Slideshow slideshow);
}
