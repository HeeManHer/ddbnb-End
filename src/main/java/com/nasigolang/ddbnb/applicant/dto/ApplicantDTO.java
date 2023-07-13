package com.nasigolang.ddbnb.applicant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.board.dto.BoardDTO;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApplicantDTO {

    private long applicantId;
    private BoardDTO board;
    private MemberSimpleDTO member;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date appliedDate;
}
