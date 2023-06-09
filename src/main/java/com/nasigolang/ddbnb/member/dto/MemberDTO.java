package com.nasigolang.ddbnb.member.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {

    private long memberId;

    private String nickname;

    private String profileImage;

    private int reportedCount;

    private String socialLogin;

    private String socialId;

    private String accessToken;

    private long accessTokenExpireDate;

    private String refreshToken;

    private long refreshTokenExpireDate;

    private String gender;

    private LocalDate signDate;

    private LocalDate deletedDate;

    private String preferredArea;

    private String petStitterCarrer;

    private String detailedHistory;

    private String period;

    private String starPoint;

    private String status;

    private LocalDate lastVisitDate;

}