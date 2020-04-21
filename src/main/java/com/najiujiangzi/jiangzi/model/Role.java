package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;


public class Role {
	private Long id;
	private String name;
	private String describe;
	private LocalDateTime create;
	private LocalDateTime update;
	private Boolean deleted;



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

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getDeleted() {
		return this.deleted;
		}

}
