package com.nasigolang.ddbnb.pet.petsitter.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "sITTER_IMAGE")
@Entity(name = "PetSitterImage")
@SequenceGenerator(name = "sitter_image_sequence_generator", sequenceName = "seq_sitter_img_id", initialValue = 1, allocationSize = 50)
public class PetSitterImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sitter_image_sequence_generator")
    @Column(name = "IMAGE_ID")
    private long imgId;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "BOARD_ID")
    private long boardId;
}
