package com.nasigolang.ddbnb.report.dto;

import com.nasigolang.ddbnb.member.entity.Member;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReportDTO {

    private int reportId;
    private LocalDate reportDate;
    private String reportReason;
    private String reportState;
    private String reportDetail;
    private String reportCategory;
    private Member currentUser;
    private Member otherUser;

}
