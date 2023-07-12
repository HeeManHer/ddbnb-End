package com.nasigolang.ddbnb.board.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BoardImageDTO {

    private long imageId;
    private String imageUrl;
    private long boardId;
}
