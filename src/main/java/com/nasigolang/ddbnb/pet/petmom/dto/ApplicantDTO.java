package com.nasigolang.ddbnb.pet.petmom.dto;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private long boardId;
    private Member member;
}



