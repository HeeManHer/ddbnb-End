package com.nasigolang.ddbnb.member.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberSimpleDTO {

    private long memberId;
    private String experience;
    private String socialLogin;
    private String profileImage;
    private String gender;
    private String nickname;
    private String preferredArea;
    private int reportedCount;
    private String status;
    private LocalDate signDate;
    private String petSitterCareer;
    private String starPoint;
    private String detailedHistory;

}