package com.example;

public class Car {
    private int id;
    private String maker;
     private String model;
    private int year;
    private double price;


    public Car(){} //obavezno je da imamo podrazumevani konstuktor ali mi mozemo kreirati i nas novi

    public Car(int id, String maker, String model, int year, double price) {
        this.id = id;
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
