package com.nasigolang.ddbnb.pet.petmom.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BoardImageDTO {

    private long imgId;
    private String imageUrl;
    private long boardId;
}
