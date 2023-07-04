package com.nasigolang.ddbnb.pet.petmom.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "BOARD_IMAGE")
@Entity(name = "BoardImage")
@SequenceGenerator(name = "boardImage_sequence_generator", sequenceName = "seq_board_img_id", initialValue = 1, allocationSize = 50)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardImage_sequence_generator")
    @Column(name = "IMAGE_ID")
    private long imgId;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "BOARD_ID")
    private long boardId;
}
