package com.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.reflect.TypeToken; //dolazi iz googlove biblioteke


public class Main {
    public static void main(String[] args) {
/*Person p = new Person ();
p.setName("John Smith");
p.setAge(27);
p.setOccupation("Doctor");

Gson gson = new Gson();
        System.out.println(gson.toJson(p));  //ovo ce ispisati {"name":"John Smith","age":27,"Occupation":"Doctor"}
        //

        String  strPerson ="{\"name\":\"Jone Doe\",\"age\":25,\"Occupation\":\"Nurse\"}";
        Person p1 = gson.fromJson(strPerson,Person.class);
        System.out.println(p1); //ispis Person{name='Jone Doe', age=25, Occupation='Nurse'} */


        File f = new File(System.getenv("Java_resources") + "/ajp3_people.json");
        try{
            Scanner s = new Scanner(System.in);
            s.useDelimiter("\\z");
            String peopleJson = s.next().trim();
            Gson gson = new Gson();//kad procitamo ceo fajl u string mozemo kreirati gson objekat
            //mozemo da napravimo ArrayListu ili niz...


            Person [] people = gson.fromJson(peopleJson,Person[].class); //formiramo niz osoba,imamo na drugi arg [] jer treba niz

            for(Person p: people){
                System.out.println(p);
            }
Type listType =new TypeToken<ArrayList<Person>>() {};//definisemo promenljivu kkoja ce nam definisati tip promenljive
            //sta u zagradi navedemo od padataka formirace mi onaj tacka class podatak
            //TypeTokken uzima  tip koji hocemo formirati.U ovom slucaju je ArrayLista-a.
            //TypeTokken je apstr. klasa i ona ce pozvati neki interfejs u pozadini koji ce se izvrsiti i onda cemo iz tog
            //interfejsa izvuci tip
            //Sta god stavimo unutar zagrade on ce nam ispisati klasu kao strukturu.
//ZNACI UMESTO Person[].class gore imamo listType kao argument dole za nasu AL
            ArrayList<Person> people1 = gson.fromJson(peopleJson,listType); //ovo je kad dobijemo taj tip
            //na osnovu gornju liste mozemo dobiti listu objekata korak iznad

            System.out.println(gson.toJson(people1)); //posaljemo strukturu koju konvertujemo u Json podatak
            //nije potrebno mapirati unazad korak daleko jednostavniji
            //sada idemo na mokaroo da napravimo neki modul da se poigramo sa Json strukturom
        }
 catch( IOException ex ){
            ex.printStackTrace();
}
    }
}