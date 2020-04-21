package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;

/**
 * 收藏图片
 */
public class EnshrineImage {
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
	 *收藏夹id
	 */
	private Long enshrineGroupId;
	/**
	 *创建时间
	 */
	private LocalDateTime create;
	/**
	 *是否删除
	 */
	private Boolean deleted;
	/**
	 *是否失效
	 */
	private Boolean loseEfficacy;


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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getEnshrineGroupId() {
		return enshrineGroupId;
	}

	public void setEnshrineGroupId(Long enshrineGroupId) {
		this.enshrineGroupId = enshrineGroupId;
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

	public Boolean getLoseEfficacy() {
		return loseEfficacy;
	}

	public void setLoseEfficacy(Boolean loseEfficacy) {
		this.loseEfficacy = loseEfficacy;
	}
}
