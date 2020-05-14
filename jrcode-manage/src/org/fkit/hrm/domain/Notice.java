package org.fkit.hrm.domain;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;        // 编号
	private String title;   // 标题
	private String content; // 内容
	private User user;        // 发布人
	private Integer menuId; //菜单id
	private String menuName; //菜单名称
	private java.util.Date createTime;  // 发布日期

	// 无参数构造器
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	// setter和getter方法
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}





	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "Notice{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", user=" + user +
				", menuId=" + menuId +
				", menuName='" + menuName + '\'' +
				", createTime=" + createTime +
				'}';
	}
}