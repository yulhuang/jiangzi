package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;


public class Test {
	/**
	 *id
	 */
	private Long id;
	/**
	 *用户id
	 */
	private Long userId;
	/**
	 *图片id
	 */
	private Long imageId;
	/**
	 *点赞时间
	 */
	private LocalDateTime create;
	/**
	 *是否取消点赞
	 */
	private Boolean deleted;



	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
		}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return this.userId;
		}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Long getImageId() {
		return this.imageId;
		}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
	public LocalDateTime getCreate() {
		return this.create;
		}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getDeleted() {
		return this.deleted;
		}

}
