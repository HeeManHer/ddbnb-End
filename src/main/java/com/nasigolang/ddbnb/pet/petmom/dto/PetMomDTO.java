package com.nasigolang.ddbnb.pet.petmom.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PetMomDTO {
    private long boardId;
    private String boardTitle;
    private String boardCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date boardDate;
    private String location;
    private String care;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String signficant;
    private String request;
    private MemberSimpleDTO member;
    private List<ApplicantDTO> apllicantList;
    private int hourlyRate;
    private int dateRate;
    private String houseType;
    private String petYN;
    private List<OtherTypeDTO> otherCondition;
    private String momStatus;

    private List<BoardImageDTO> boardImage;

}