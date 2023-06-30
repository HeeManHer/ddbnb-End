package com.nasigolang.ddbnb.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberSimpleDTO {

    private long memberId;
    private String nickname;
    private String profileImage;
    private String gender;

    private String experience;
    private String preferredArea;
    private String petSitterCareer;
    private String detailedHistory;

    private String starPoint;
    private String status;
    private int reportedCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate signDate;

    private String socialLogin;

}