package com.nasigolang.ddbnb.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfileDTO {

	private long id;
	private String connected_at;
	private KakaoAccount kakao_account;

	public KakaoProfileDTO() {}

	public KakaoProfileDTO(long id, String connected_at, KakaoAccount kakao_account) {
		this.id = id;
		this.connected_at = connected_at;
		this.kakao_account = kakao_account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public KakaoAccount getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(
		KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}

	@Override
	public String toString() {
		return "KakaoProfileDTO{" +
			"id=" + id +
			", connected_at='" + connected_at + '\'' +
			", kakao_account=" + kakao_account +
			'}';
	}

	public class KakaoAccount {



		public KakaoAccount() {}

	}

	public class profile{

		private String nickname;
		private String profile_image_url;
		private boolean is_default_image;

		public profile(String nickname, String profile_image_url, boolean is_default_image) {
			this.nickname = nickname;
			this.profile_image_url = profile_image_url;
			this.is_default_image = is_default_image;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getProfile_image_url() {
			return profile_image_url;
		}

		public void setProfile_image_url(String profile_image_url) {
			this.profile_image_url = profile_image_url;
		}

		public boolean isIs_default_image() {
			return is_default_image;
		}

		public void setIs_default_image(boolean is_default_image) {
			this.is_default_image = is_default_image;
		}

		@Override
		public String toString() {
			return "profile{" +
					"nickname='" + nickname + '\'' +
					", profile_image_url='" + profile_image_url + '\'' +
					", is_default_image=" + is_default_image +
					'}';
		}
	}
}
