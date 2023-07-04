package com.nasigolang.ddbnb.pet.petsitter.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetSitterImageDTO {

    private long imgId;
    private String imageUrl;
    private long boardId;
}
