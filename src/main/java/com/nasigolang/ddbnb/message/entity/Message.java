package com.nasigolang.ddbnb.message.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "MESSAGE")
@Entity(name = "Message")
@SequenceGenerator(name = "MESSAGE_SEQ_GENERATOR", sequenceName = "SEQ_MESSAGE_ID", initialValue = 1, allocationSize = 1)
public class Message {

    @Id
    @Column(name = "MSG_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ_GENERATOR")
    @Comment("쪽지 번호")
    private long msgId;

    @Column(name = "MSG_CONTENT")
    @Comment("내용")
    private String msgContent;

    @Column(name = "WRITE_DATE")
    @Comment("작성일")
    private Date writeDate;

    @ManyToOne
    @JoinColumn(name = "WHO", referencedColumnName = "MEMBER_ID")
    @Comment("누가")
    private Member who;

    @ManyToOne
    @JoinColumn(name = "WHOM", referencedColumnName = "MEMBER_ID")
    @Comment("누구에게")
    private Member whom;

}
