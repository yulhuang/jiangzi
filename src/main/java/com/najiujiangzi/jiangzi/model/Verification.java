package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;


public class Verification {
	/**
	*id
	*/
	private Long id;
	/**
	*验证码
	*/
	private String code;
	/**
	 *邮箱
	*/
	private String email;
	/**
	*null
	*/
	private LocalDateTime create;



	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
		}

	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
		}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
		}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
	public LocalDateTime getCreate() {
		return this.create;
		}

}
