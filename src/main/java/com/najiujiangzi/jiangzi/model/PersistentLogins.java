package com.najiujiangzi.jiangzi.model;

import java.time.LocalDateTime;


public class PersistentLogins {
	/**
	 *null
	 */
	private String username;
	/**
	 *null
	 */
	private String series;
	/**
	 *null
	 */
	private String token;
	/**
	 *null
	 */
	private LocalDateTime last_used;



	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return this.username;
		}

	public void setSeries(String series) {
		this.series = series;
	}
	public String getSeries() {
		return this.series;
		}

	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return this.token;
		}

	public void setLast_used(LocalDateTime last_used) {
		this.last_used = last_used;
	}
	public LocalDateTime getLast_used() {
		return this.last_used;
		}

}
