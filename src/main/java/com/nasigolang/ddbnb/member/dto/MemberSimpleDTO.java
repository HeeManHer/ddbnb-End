package com.nasigolang.ddbnb.member.dto;

import java.net.URL;

public class MemberSimpleDTO {

    private long memberId;

    private String nickname;

    private String profileImage;

    private String linkToMyPage;


    public MemberSimpleDTO() {}

    public MemberSimpleDTO(long memberId, String nickname, String profileImage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public MemberSimpleDTO(long memberId, String nickname, String profileImage, String linkToMyPage) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.linkToMyPage = linkToMyPage;
        this.profileImage = profileImage;
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

    public String getLinkToMyPage() {
        return linkToMyPage;
    }

    public void setLinkToMyPage(String linkToMyPage) {
        this.linkToMyPage = linkToMyPage;
    }

    public String getprofileImage() {
        return profileImage;
    }

    public void setprofileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "MemberSimpleDTO{" +
                "memberId=" + memberId +
                ", nickname='" + nickname + '\'' +
                ", imageSource=" + profileImage +
                ", linkToMyPage='" + linkToMyPage + '\'' +
                '}';
    }
}
