package com.nasigolang.ddbnb.report.entity;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "REPORT")
@Entity(name = "Report")
@SequenceGenerator(name = "REPORT_SEQ_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_REPORT_ID")
public class Report {
    @Id
    @Column(name = "REPORT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_SEQ_GENERATOR")
    private int reportId;

    @Column(name = "REPORT_DATE")
    private LocalDate reportDate;

    @Column(name = "REPORT_STATE")
    private String reportState;

    @Column(name = "REPORTED_CATEGORT")
    private String reportCategory;

    @Column(name = "REPORTED_REASON")
    private String reportReason;

    @Column(name = "REPORTED_DETATIL")
    private String reportDetail;

    @ManyToOne
    @JoinColumn(name = "CURRENT_USER_ID")
    private Member currentUser;

    @ManyToOne
    @JoinColumn(name = "OTHER_USER_ID")
    private Member otherUser;

    @Column(name ="BOARD_ID")
    private long boardId;

}
