package com.najiujiangzi.jiangzi.model;


public class ImageGroup {
	/**
	 *id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 *组名
	 */
	private String name;
	/**
	 *组描述
	 */
	private String describe;
	/**
	 *组类型，1：公开，2：私有
	 */
	private Integer type;
	/**
	 *组中图片数
	 */
	private Integer image_count;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
		}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
		}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return this.describe;
		}

	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getType() {
		return this.type;
		}

	public void setImage_count(Integer image_count) {
		this.image_count = image_count;
	}
	public Integer getImage_count() {
		return this.image_count;
		}

}
