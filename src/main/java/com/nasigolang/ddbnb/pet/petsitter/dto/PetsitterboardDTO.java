package com.nasigolang.ddbnb.pet.petsitter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nasigolang.ddbnb.member.dto.MemberDTO;
import com.nasigolang.ddbnb.member.dto.MemberSimpleDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

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
}
