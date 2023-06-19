package com.nasigolang.ddbnb.Pet.petmom.dto;

import com.nasigolang.ddbnb.Pet.petmom.entity.PetMom;
import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private PetMom petMom;
    private Member member;
}

