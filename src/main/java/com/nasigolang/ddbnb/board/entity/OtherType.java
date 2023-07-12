package com.nasigolang.ddbnb.board.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "OtherType")
@Table(name = "OTHER_TYPE")
public class OtherType {

    @Id
    @Column(name = "TYPE_ID")
    @Comment("조건 번호")
    private long typeId;

    @Column(name = "TYPE_CONTENT")
    @Comment("조건 내용")
    private String typeContent;

    @ManyToMany(mappedBy = "otherCondition", cascade = CascadeType.REMOVE)
    private List<PetMom> boards = new ArrayList<>();
}
