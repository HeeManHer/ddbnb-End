package com.nasigolang.ddbnb.login.dto;

public class AccessTokenDTO {

	private String grantType;
	private long memberId;
	private String accessToken;
	private long accessTokenExpiresIn;

	public AccessTokenDTO() {}

	public AccessTokenDTO(String grantType, long memberId, String accessToken,
		long accessTokenExpiresIn) {
		this.grantType = grantType;
		this.memberId = memberId;
		this.accessToken = accessToken;
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	public void setAccessTokenExpiresIn(long accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	@Override
	public String toString() {
		return "AcessTokenDTO{" +
			"grantType='" + grantType + '\'' +
			", memberId=" + memberId +
			", accessToken='" + accessToken + '\'' +
			", accessTokenExpiresIn=" + accessTokenExpiresIn +
			'}';
	}
}
