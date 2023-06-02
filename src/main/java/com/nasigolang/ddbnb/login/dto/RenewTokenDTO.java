package com.nasigolang.ddbnb.login.dto;

public class RenewTokenDTO {

	private String token_type;
	private String access_token;
	private String id_token;
	private Integer expires_in;
	private String refresh_token;
	private Integer refresh_token_expires_in;

	public RenewTokenDTO () {}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getId_token() {
		return id_token;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public Integer getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}

	public void setRefresh_token_expires_in(Integer refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
	}

	@Override
	public String toString() {
		return "RenewTokenDTO{" +
			"token_type='" + token_type + '\'' +
			", access_token='" + access_token + '\'' +
			", id_token='" + id_token + '\'' +
			", expires_in=" + expires_in +
			", refresh_token='" + refresh_token + '\'' +
			", refresh_token_expires_in=" + refresh_token_expires_in +
			'}';
	}
}
