package com.nasigolang.ddbnb.login.dto;

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

		private boolean has_email;
		private boolean email_needs_agreement;
		private boolean is_email_valid;
		private boolean is_email_verified;
		private String email;
		private boolean has_gender;
		private boolean gender_needs_agreement;
		private String gender;

		public KakaoAccount() {}

		public KakaoAccount(boolean has_email, boolean email_needs_agreement,
			boolean is_email_valid,
			boolean is_email_verified, String email, boolean has_gender,
			boolean gender_needs_agreement,
			String gender) {
			this.has_email = has_email;
			this.email_needs_agreement = email_needs_agreement;
			this.is_email_valid = is_email_valid;
			this.is_email_verified = is_email_verified;
			this.email = email;
			this.has_gender = has_gender;
			this.gender_needs_agreement = gender_needs_agreement;
			this.gender = gender;
		}

		public boolean isHas_email() {
			return has_email;
		}

		public void setHas_email(boolean has_email) {
			this.has_email = has_email;
		}

		public boolean isEmail_needs_agreement() {
			return email_needs_agreement;
		}

		public void setEmail_needs_agreement(boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
		}

		public boolean isIs_email_valid() {
			return is_email_valid;
		}

		public void setIs_email_valid(boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}

		public boolean isIs_email_verified() {
			return is_email_verified;
		}

		public void setIs_email_verified(boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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
				"has_email=" + has_email +
				", email_needs_agreement=" + email_needs_agreement +
				", is_email_valid=" + is_email_valid +
				", is_email_verified=" + is_email_verified +
				", email='" + email + '\'' +
				", has_gender=" + has_gender +
				", gender_needs_agreement=" + gender_needs_agreement +
				", gender='" + gender + '\'' +
				'}';
		}
	}
}
