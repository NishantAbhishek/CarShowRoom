package com.example.carshowroom.Models;

public class CarBought {

    private String Contact;
    private String Description;
    private String ImageUrl;
    private String Name;
    private String Price;

    public CarBought() {
    }

    public CarBought(String contact, String description, String imageUrl, String name, String price) {
        Contact = contact;
        Description = description;
        ImageUrl = imageUrl;
        Name = name;
        Price = price;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
