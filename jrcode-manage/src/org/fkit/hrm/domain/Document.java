package org.fkit.hrm.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;                    // 编号
	private String title;            // 标题
	private String fileName;        // 文件名
	private MultipartFile file;        // 文件
	private String remark;            // 描述
	private String path;            // 描述
	private java.util.Date createTime;    // 上传时间
	private User user;                // 上传人

	// 无参数构造器
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	// setter和getter方法

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Document{" +
				"id=" + id +
				", title='" + title + '\'' +
				", fileName='" + fileName + '\'' +
				", file=" + file +
				", remark='" + remark + '\'' +
				", path='" + path + '\'' +
				", createTime=" + createTime +
				", user=" + user +
				'}';
	}
}