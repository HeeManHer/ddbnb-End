package com.nasigolang.ddbnb.pet.petmom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private PetMomDTO boardId;
    private MemberSimpleDTO member;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appliedDate;
}