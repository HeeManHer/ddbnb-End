package com.nasigolang.ddbnb.pet.Applicant.dto;


import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private MemberSimpleDTO member;
    private PetsitterboardDTO boardId;

}
