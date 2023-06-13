package com.nasigolang.ddbnb.Pet.petmom.dto;

import com.nasigolang.ddbnb.member.dto.MemberDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PetMomDTO {

    private int petMomId;

    private String petMomTitle;

    private String petMomCategory;

    private Date petMomDate;

    private String Care;

    private LocalDate startDate;

    private LocalDate endDate;

    private String location;

    private String signficant;

    private String request;

    private byte[] img;

    private int hourlyRate;

    private int dateRate;

    private String  houseType;

    private boolean petYN;

    private MemberDTO memberId;

}
