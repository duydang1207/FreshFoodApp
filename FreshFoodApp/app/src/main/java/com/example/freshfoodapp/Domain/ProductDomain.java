package com.example.freshfoodapp.Domain;

public class ProductDomain {
    private String title;
    private String pic;
    private Double fee;
    private int id;

    public ProductDomain(String title, String pic, Double fee, int id) {
        this.title = title;
        this.pic = pic;
        this.fee = fee;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
