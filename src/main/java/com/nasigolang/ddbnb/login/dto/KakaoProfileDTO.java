package com.nasigolang.ddbnb.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfileDTO {

	private long id;
	private String connected_at;
	private Properties properties;
	private KakaoAccount kakao_account;

	public KakaoProfileDTO() {
	}

	public KakaoProfileDTO(long id, String connected_at, Properties properties, KakaoAccount kakao_account) {
		this.id = id;
		this.connected_at = connected_at;
		this.properties = properties;
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

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public KakaoAccount getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}

	@Override
	public String toString() {
		return "KakaoProfileDTO{" +
				"id=" + id +
				", connected_at='" + connected_at + '\'' +
				", properties=" + properties +
				", kakao_account=" + kakao_account +
				'}';
	}



	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Properties {
		private String nickname;
		private String profile_image;

		public Properties() {
		}

		public Properties(String nickname, String profile_image) {
			this.nickname = nickname;
			this.profile_image = profile_image;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getProfile_image() {
			return profile_image;
		}

		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}

		@Override
		public String toString() {
			return "Properties{" +
					"nickname='" + nickname + '\'' +
					", profile_image='" + profile_image + '\'' +
					'}';
		}
	}
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class KakaoAccount {
			private boolean profile_nickname_needs_agreement;
			private boolean profile_image_needs_agreement;
			private Profile profile;
			private boolean has_gender;
			private boolean gender_needs_agreement;
			private String gender;

			public KakaoAccount() {
			}

			public KakaoAccount(boolean profile_nickname_needs_agreement, boolean profile_image_needs_agreement, Profile profile, boolean has_gender, boolean gender_needs_agreement, String gender) {
				this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
				this.profile_image_needs_agreement = profile_image_needs_agreement;
				this.profile = profile;
				this.has_gender = has_gender;
				this.gender_needs_agreement = gender_needs_agreement;
				this.gender = gender;
			}

			public boolean isProfile_nickname_needs_agreement() {
				return profile_nickname_needs_agreement;
			}

			public void setProfile_nickname_needs_agreement(boolean profile_nickname_needs_agreement) {
				this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
			}

			public boolean isProfile_image_needs_agreement() {
				return profile_image_needs_agreement;
			}

			public void setProfile_image_needs_agreement(boolean profile_image_needs_agreement) {
				this.profile_image_needs_agreement = profile_image_needs_agreement;
			}

			public Profile getProfile() {
				return profile;
			}

			public void setProfile(Profile profile) {
				this.profile = profile;
			}

			public boolean isHas_gender() {
				return has_gender;
			}

			public void setHas_gender(boolean has_gender) {
				this.has_gender = has_gender;
			}

			public boolean isGender_needs_agreement() {
				return gender_needs_agreement;
			}

			public void setGender_needs_agreement(boolean gender_needs_agreement) {
				this.gender_needs_agreement = gender_needs_agreement;
			}

			public String getGender() {
				return gender;
			}

			public void setGender(String gender) {
				this.gender = gender;
			}

			@Override
			public String toString() {
				return "KakaoAccount{" +
						"profile_nickname_needs_agreement=" + profile_nickname_needs_agreement +
						", profile_image_needs_agreement=" + profile_image_needs_agreement +
						", profile=" + profile +
						", has_gender=" + has_gender +
						", gender_needs_agreement=" + gender_needs_agreement +
						", gender='" + gender + '\'' +
						'}';
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			public class Profile {
				private String nickname;
				private String profile_image_url;
				private boolean is_default_image;

				public Profile() {
				}

				public Profile(String nickname, String profile_image_url, boolean is_default_image) {
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
					return "Profile{" +
							"nickname='" + nickname + '\'' +
							", profile_image_url='" + profile_image_url + '\'' +
							", is_default_image=" + is_default_image +
							'}';
				}
			}
	}
}