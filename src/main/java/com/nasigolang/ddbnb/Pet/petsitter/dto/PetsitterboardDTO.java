package com.nasigolang.ddbnb.pet.petsitter.dto;

import com.nasigolang.ddbnb.member.dto.MemberDTO;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetsitterboardDTO {

    private long boardId;
    private String boardTitle;
    private MemberDTO memberId;
    private LocalDate boardDate;
    private String location;
    private String care;
    private LocalDate startDate;
    private LocalDate endDate;
    private String signficant;
    private byte[] img;
    private String request;
    private String boardCategory;
    private int rate;
    private String petName;
    private int petAge;
    private String petShape;
    private String petGender;
    private String petSize;
}
