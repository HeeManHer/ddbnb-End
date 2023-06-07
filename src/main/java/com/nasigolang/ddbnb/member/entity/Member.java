package com.nasigolang.ddbnb.member.entity;

import java.net.URL;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "Member")
@Table(name = "member")
@SequenceGenerator(
        name = "member_sequence_generator",
        sequenceName = "sequence_member_id",
        initialValue = 1,
        allocationSize = 50
)
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence_generator"
    )
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "reported_count", nullable = false)
    private int reportedCount;

    @Column(name = "social_login", nullable = false)
    private String socialLogin;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "access_token_expire_date", nullable = false)
    private long accessTokenExpireDate;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "refresh_token_expire_date", nullable = false)
    private long refreshTokenExpireDate;


    @Column(name = "gender")
    private String gender;


    @Column(name = "sign_date", nullable = false)
    private LocalDateTime signDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "preferred_area")
    private String preferredArea;

    @Column(name = "per_sitter_career")
    private String petStitterCarrer;

    @Column(name = "detailed_history")
    private String detailedHistory;

    @Column(name = "period")
    private String period;

    @Column(name = "star_point")
    private String starPoint;

    @Column(name = "status")
    private String status;

    public Member(long memberId, String nickname, String profileImage, int reportedCount, String socialLogin, String socialId, String accessToken, long accessTokenExpireDate, String refreshToken, long refreshTokenExpireDate, String gender, LocalDateTime signDate, LocalDateTime deletedDate, String preferredArea, String petStitterCarrer, String detailedHistory, String period, String starPoint, String status) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.reportedCount = reportedCount;
        this.socialLogin = socialLogin;
        this.socialId = socialId;
        this.accessToken = accessToken;
        this.accessTokenExpireDate = accessTokenExpireDate;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireDate = refreshTokenExpireDate;
        this.gender = gender;
        this.signDate = signDate;
        this.deletedDate = deletedDate;
        this.preferredArea = preferredArea;
        this.petStitterCarrer = petStitterCarrer;
        this.detailedHistory = detailedHistory;
        this.period = period;
        this.starPoint = starPoint;
        this.status = status;
    }

    public Member() {

    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public void setReportedCount(int reportedCount) {
        this.reportedCount = reportedCount;
    }

    public String getSocialLogin() {
        return socialLogin;
    }

    public void setSocialLogin(String socialLogin) {
        this.socialLogin = socialLogin;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getAccessTokenExpireDate() {
        return accessTokenExpireDate;
    }

    public void setAccessTokenExpireDate(long accessTokenExpireDate) {
        this.accessTokenExpireDate = accessTokenExpireDate;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getRefreshTokenExpireDate() {
        return refreshTokenExpireDate;
    }

    public void setRefreshTokenExpireDate(long refreshTokenExpireDate) {
        this.refreshTokenExpireDate = refreshTokenExpireDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDateTime signDate) {
        this.signDate = signDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getPreferredArea() {
        return preferredArea;
    }

    public void setPreferredArea(String preferredArea) {
        this.preferredArea = preferredArea;
    }

    public String getPetStitterCarrer() {
        return petStitterCarrer;
    }

    public void setPetStitterCarrer(String petStitterCarrer) {
        this.petStitterCarrer = petStitterCarrer;
    }

    public String getDetailedHistory() {
        return detailedHistory;
    }

    public void setDetailedHistory(String detailedHistory) {
        this.detailedHistory = detailedHistory;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(String starPoint) {
        this.starPoint = starPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", nickname='" + nickname + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", reportedCount=" + reportedCount +
                ", socialLogin='" + socialLogin + '\'' +
                ", socialId='" + socialId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpireDate=" + accessTokenExpireDate +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpireDate=" + refreshTokenExpireDate +
                ", gender='" + gender + '\'' +
                ", signDate=" + signDate +
                ", deletedDate=" + deletedDate +
                ", preferredArea='" + preferredArea + '\'' +
                ", petStitterCarrer='" + petStitterCarrer + '\'' +
                ", detailedHistory='" + detailedHistory + '\'' +
                ", period='" + period + '\'' +
                ", starPoint='" + starPoint + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
