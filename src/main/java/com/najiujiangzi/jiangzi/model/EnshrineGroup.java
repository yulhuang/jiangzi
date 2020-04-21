package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;

/**
 * 收藏夹
 */
public class EnshrineGroup {
	/**
	 *id
	 */
	private Long id;
	/**
	 *用户id
	 */
	private Long userId;
	/**
	 *收藏夹名称
	 */
	private String name;
	/**
	 *内容数量
	 */
	private Integer numberContent;
	/**
	 *创建时间
	 */
	private LocalDateTime create;
	/**
	 *是否删除
	 */
	private Boolean deleted;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberContent() {
		return numberContent;
	}

	public void setNumberContent(Integer numberContent) {
		this.numberContent = numberContent;
	}

	public LocalDateTime getCreate() {
		return create;
	}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
