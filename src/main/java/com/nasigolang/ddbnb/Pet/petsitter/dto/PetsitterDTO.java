package com.nasigolang.ddbnb.Pet.petsitter.dto;

public class PetsitterDTO {

    private long boardId;
    private int rate;
    private String petName;
    private int petAge;
    private String petShape;
    private String petGender;
    private String petSize;

    public PetsitterDTO() {}

    public PetsitterDTO(long boardId, int rate, String petName, int petAge, String petShape, String petGender, String petSize) {
        this.boardId = boardId;
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
        return "PetsitterDTO{" +
                "boardId=" + boardId +
                ", rate=" + rate +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petShape='" + petShape + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petSize='" + petSize + '\'' +
                '}';
    }
}
