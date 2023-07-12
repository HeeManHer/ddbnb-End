package com.nasigolang.ddbnb.board.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "PetSitter")
@Table(name = "PET_SITTER")
public class PetSitter extends Board {

    @Column(name = "RATE")
    @Comment("가격")
    private int rate;

    @Column(name = "PET_NAME")
    @Comment("펫 이름")
    private String petName;

    @Column(name = "PET_AGE")
    @Comment("펫 나이")
    private int petAge;

    @Column(name = "PET_SHAPE")
    @Comment("펫 종류")
    private String petShape;

    @Column(name = "PET_GENDER")
    @Comment("펫 성별")
    private String petGender;

    @Column(name = "PET_SIZE")
    @Comment("펫 크기")
    private String petSize;
}