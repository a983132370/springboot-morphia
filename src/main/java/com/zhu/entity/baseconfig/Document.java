package com.zhu.entity.baseconfig;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by zhu
 */
public abstract class Document {
	@Id
	protected String id;

	protected Integer status;

	// 创建时间
	protected Integer createdTime;

	// 修改时间
	protected Integer modifiedTime;

	public Integer getStatus() {
		return status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Integer modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
