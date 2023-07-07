package com.nasigolang.ddbnb.pet.Applicant.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import com.nasigolang.ddbnb.pet.petsitter.dto.PetsitterboardDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicantDTO {

    private int applicantId;
    private MemberSimpleDTO member;
    private PetsitterboardDTO boardId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appliedDate;

}
