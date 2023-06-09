package com.nasigolang.ddbnb.Pet;

import java.util.Arrays;
import java.util.Date;

public class BorderDTO {

    private int boardId;
    private String boardTitle;
    private String boardCategory;
    private Date boardDate;
    private String Care;
    private Date startDate;
    private Date endDate;
    private String signficant;
    private String request;
    private byte[] img;
    public BorderDTO() {}

    public BorderDTO(int boardId, String boardTitle, String boardCategory, Date boardDate,
                     String care, Date startDate, Date endDate, String signficant,
                     String request, byte[] img) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardDate = boardDate;
        Care = care;
        this.startDate = startDate;
        this.endDate = endDate;
        this.signficant = signficant;
        this.request = request;
        this.img = img;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public Date getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(Date boardDate) {
        this.boardDate = boardDate;
    }

    public String getCare() {
        return Care;
    }

    public void setCare(String care) {
        Care = care;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSignficant() {
        return signficant;
    }

    public void setSignficant(String signficant) {
        this.signficant = signficant;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "BorderDTO{" +
                "boardId=" + boardId +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardCategory='" + boardCategory + '\'' +
                ", boardDate=" + boardDate +
                ", Care='" + Care + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", signficant='" + signficant + '\'' +
                ", request='" + request + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
