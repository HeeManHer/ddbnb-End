package com.nasigolang.ddbnb.Pet.petmom.dto;


import com.nasigolang.ddbnb.member.dto.MemberDTO;
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
    private int boardId;
    private String boardTitle;
    private String boardCategory;
    private LocalDate boardDate;
    private String location;
    private String care;
    private LocalDate startDate;
    private LocalDate endDate;
    private String signficant; // 특이사항
    private String request; // 요청사항
    private MemberDTO memberId;
    private List<ApplicantDTO> apllicantList;
    private int hourlyRate;
    private int dateRate;
    private String houseType;
    private boolean petYN;
    private List<OtherTypeDTO> otherCondition;
}
