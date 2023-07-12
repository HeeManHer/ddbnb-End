package com.nasigolang.ddbnb.board.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PetMomDTO extends BoardDTO {

    private int hourlyRate;
    private int dateRate;
    private String houseType;
    private String petYN;
    private List<OtherTypeDTO> otherCondition;
}
