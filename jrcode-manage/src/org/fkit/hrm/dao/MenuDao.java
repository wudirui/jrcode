package org.fkit.hrm.dao;

import org.apache.ibatis.annotations.Select;
import org.fkit.hrm.domain.Menu;

import java.util.List;

import static org.fkit.hrm.util.common.HrmConstants.MENU;
import static org.fkit.hrm.util.common.HrmConstants.PARENT_MENU;

public interface MenuDao {
	@Select("select * from " + MENU)
	List<Menu> findMenuList();

	@Select("select * from " + PARENT_MENU)
	List<Menu> findParentMenuList();
}
