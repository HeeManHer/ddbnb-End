package com.nasigolang.ddbnb.board.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "BOARD_IMAGE")
@Entity(name = "BoardImage")
@SequenceGenerator(name = "BOARD_IMG_SEQ_GENERATOR", sequenceName = "SEQ_IMG_ID", initialValue = 1, allocationSize = 50)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_IMG_SEQ_GENERATOR")
    @Column(name = "IMAGE_ID")
    @Comment("이미지 번호")
    private long imageId;

    @Column(name = "IMAGE_URL")
    @Comment("이미지 주소")
    private String imageUrl;

    @Column(name = "BOARD_ID")
    @Comment("게시판 번호")
    private long boardId;
}
