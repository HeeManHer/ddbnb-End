package com.nasigolang.ddbnb.report.dto;
import java.util.Date;
public class ReportDTO {

    private int reportId;

    private Date reportDate;

    private String reportReason;

    private String reportState;

    private String reportDetail;

    private String reportCategory;

    public ReportDTO(){}

    public ReportDTO(int reportId, Date reportDate, String reportReason, String reportState, String reportDetail, String reportCategory) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.reportReason = reportReason;
        this.reportState = reportState;
        this.reportDetail = reportDetail;
        this.reportCategory = reportCategory;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportState() {
        return reportState;
    }

    public void setReportState(String reportState) {
        this.reportState = reportState;
    }

    public String getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(String reportDetail) {
        this.reportDetail = reportDetail;
    }

    public String getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "reportId=" + reportId +
                ", reportDate=" + reportDate +
                ", reportReason='" + reportReason + '\'' +
                ", reportState='" + reportState + '\'' +
                ", reportDetail='" + reportDetail + '\'' +
                ", reportCategory='" + reportCategory + '\'' +
                '}';
    }
}
