package org.example;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Person { //ova klasa znaci predstavlja model
    //znaci mvc se zasniva da se model napravi potpuno nezavisno od kontrolera i pogleda i na taj nacin
    //se veoma lako postize prezentacioni sloj logike dok same domenske klase ostaju nepromenjene
    //prezentacioni sloj je zapravo VIEW
    //sam korisnik operise sa prezentacionim slojem aplikacije
    //kontroler se brine da promene na prezentacionom sloju i promene na sloju modela budu medjusobno sinhronizovane.
    //kontroler se ponasa kao posrednik izmedju pogleda i modela.
    //Svaki UI fajl dodeljen fxml-om moze imati jedan kontroler
    //kontroler dodajemo na korenom elementu fxml Ui-a(add_person.fxml  u nasem slucaju)
    //znaci unutar korenog elementa(GridPane dodamo atribut fx:controler="putanja do klase kontrolera" )...
    private final StringProperty firstName= new SimpleStringProperty(this,"firstName","");
    private final StringProperty lastName= new SimpleStringProperty(this,"lastName","");
    private final StringProperty email= new SimpleStringProperty(this,"emailName","");
    private final StringProperty address= new SimpleStringProperty(this,"address","");
    private final StringProperty country= new SimpleStringProperty(this,"country","");
    //nismo stavili buttone u ovaj kontroler jer za unutar metoda ispod radimo samo sa ovim Propertyjima iznad


    public Person(){

    }
    public Person(String firstName){
        this.firstName.set(firstName);
    }

    public Person(String firstName,String lastName){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public Person(String firstName,String lastName,String email,String address,String country){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.email.set(email);
        this.address.set(address);
        this.country.set(country);

    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void f(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    //***



    private final ObjectProperty<ArrayList<String>>errorList= new SimpleObjectProperty<>(this,"errorList",new ArrayList<>());
    public ObjectProperty<ArrayList<String>>errorsProperty(){
        return errorList;
    }

    public boolean isValid(){ //za svaku od metoda u ovom kontroleru se proveravo da li je validno...
        boolean isValid=true;
        if(firstName.get() !=null && firstName.get().equals("")) {
            errorList.getValue().add("first name cannot be empty");
            isValid=false;
            //znaci verovatno se racunaju prazna mesta znaci ako nema slova ni brojeva samo space onda ide error i ukoliko je unos prazan
        }
        if(lastName.get() !=null && lastName.get().equals("")) {
            errorList.getValue().add("last name cannot be empty");
            isValid=false;
        }
        if(email.get() !=null && email.get().equals("")) {
            errorList.getValue().add("email  cannot be empty");
            isValid=false;
        }
        if(address.get() !=null && address.get().equals("")) {
            errorList.getValue().add("address  cannot be empty");
            isValid=false;
        }
        if(country.get() !=null && country.get().equals("")) {
            errorList.getValue().add("countryi cannot be empty");
            isValid=false;
        }

        return isValid;
    }
    public boolean save(){//objekti tipa Person se cuvaju
        if(isValid()){ //ukoliko je instancs validna
            System.out.println("Person with details: \n" +this + "\nsaved!");

        }

        return false;
    }
    @Override
    public String toString(){
        return "First name :" + firstName.get().toString()+ "\n Last name" + lastName.get().toString() + "\nEmail :" +email.get().toString()+
                "\naddress : " + address.get().toString() + "\ncountry : " + country.get().toString();
    }
    }

