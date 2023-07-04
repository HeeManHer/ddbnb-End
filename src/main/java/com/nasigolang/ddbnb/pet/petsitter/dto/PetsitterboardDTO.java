package com.nasigolang.ddbnb.pet.petsitter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PetsitterboardDTO {
    private long boardId;
    private String boardTitle;
    private MemberSimpleDTO member;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate boardDate;
    private String location;
    private String care;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    private String sitterStatus;
    private List<PetSitterImageDTO> boardImage;
}
