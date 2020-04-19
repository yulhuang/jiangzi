package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;


public class Image {
	/**
	 *id
	 */
	private Long id;
	/**
	 *用户id
	 */
	private Long user_id;
	/**
	 *图片组id
	 */
	private Long image_group_id;
	/**
	 *图片url
	 */
	private String image_url;
	/**
	 *描述
	 */
	private String describe;
	/**
	 *创建时间
	 */
	private LocalDateTime create;
	/**
	 *更新时间
	 */
	private LocalDateTime update;
	/**
	 *图片类型，1：公开，2：私有
	 */
	private Integer type;



	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
		}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getUser_id() {
		return this.user_id;
		}

	public void setImage_group_id(Long image_group_id) {
		this.image_group_id = image_group_id;
	}
	public Long getImage_group_id() {
		return this.image_group_id;
		}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_url() {
		return this.image_url;
		}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return this.describe;
		}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
	public LocalDateTime getCreate() {
		return this.create;
		}

	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}
	public LocalDateTime getUpdate() {
		return this.update;
		}

	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getType() {
		return this.type;
		}

}
