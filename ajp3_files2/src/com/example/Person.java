package com.example;

public class Person {
    private String name; //znaci strukturu iz notepada++ ne smemo da menjamo
    //na mwn repository sajtu smo skinuli  gson-2.10.jar i  mysql-connector-java-8.0.30.jar
    private int age;
    private String Occupation;

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Occupation='" + Occupation + '\'' +
                '}';
    }
}
