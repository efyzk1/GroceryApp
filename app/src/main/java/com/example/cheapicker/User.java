package com.example.cheapicker;

public class User {

    String img_url,martimg_url,name,price,weight,description;

    public  User(){}


    public User(String img_url, String martimg_url, String name, String price, String weight,String description) {
        this.img_url = img_url;
        this.martimg_url = martimg_url;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMartimg_url() {
        return martimg_url;
    }

    public void setMartimg_url(String martimg_url) {
        this.martimg_url = martimg_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
}
