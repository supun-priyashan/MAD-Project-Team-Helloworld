package com.example.daytoday;

public class Debt {


    private String name;
    private String date;
    private Float amount;
    private String discription;

    public Debt() {
    }

    public Debt(String name, String date, Float amount, String discription) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.discription = discription;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String description) {
        this.discription = description;
    }



}
