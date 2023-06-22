package com.nasigolang.ddbnb.pet.Applicant.dto;


import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private Member member;
    private long boardId;

}
