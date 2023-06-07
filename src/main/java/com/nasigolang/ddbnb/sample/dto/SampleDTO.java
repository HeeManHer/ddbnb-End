package com.nasigolang.ddbnb.sample.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SampleDTO {

    private int memberId;
    private String nickname;
    private String status;
    private Date signDate;
    private int reportedCount;
}
