package com.nasigolang.ddbnb.Pet.petsitter.dto;

import java.util.Arrays;
import java.util.Date;

public class PetsitterboardDTO {

    private long boardId;
    private String boardTitle;
    private long memberId;
    private Date boardDate;
    private String location;
    private String care;
    private Date startDate;
    private Date endDate;
    private String signficant;
    private byte[] img;
    private String request;
    private String boardCategory;
    private int rate;
    private String petName;
    private int petAge;
    private String petShape;
    private String petGender;
    private String petSize;

    public PetsitterboardDTO() {}

    public PetsitterboardDTO(long boardId, String boardTitle, long memberId, Date boardDate, String location, String care, Date startDate,
                             Date endDate, String signficant, byte[] img, String request, String boardCategory, int rate, String petName,
                             int petAge, String petShape, String petGender, String petSize) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.memberId = memberId;
        this.boardDate = boardDate;
        this.location = location;
        this.care = care;
        this.startDate = startDate;
        this.endDate = endDate;
        this.signficant = signficant;
        this.img = img;
        this.request = request;
        this.boardCategory = boardCategory;
        this.rate = rate;
        this.petName = petName;
        this.petAge = petAge;
        this.petShape = petShape;
        this.petGender = petGender;
        this.petSize = petSize;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public Date getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(Date boardDate) {
        this.boardDate = boardDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public String getPetShape() {
        return petShape;
    }

    public void setPetShape(String petShape) {
        this.petShape = petShape;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    @Override
    public String toString() {
        return "PetsitterboardDTO{" +
                "boardId=" + boardId +
                ", boardTitle='" + boardTitle + '\'' +
                ", memberId=" + memberId +
                ", boardDate=" + boardDate +
                ", location='" + location + '\'' +
                ", care='" + care + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", signficant='" + signficant + '\'' +
                ", img=" + Arrays.toString(img) +
                ", request='" + request + '\'' +
                ", boardCategory='" + boardCategory + '\'' +
                ", rate=" + rate +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petShape='" + petShape + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petSize='" + petSize + '\'' +
                '}';
    }
}
