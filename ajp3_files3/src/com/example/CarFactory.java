package com.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CarFactory {

    public static ArrayList<Car> fromFile(File f)throws IOException {
        Gson gson = null;
        String carsJson = null;
        Type listType = null;
        try { //znaci ovaj try catch smo cutovali iz main
            Scanner s = new Scanner(f);
            s.useDelimiter("\\z"); // znc citamo celi fajl
            carsJson = s.next().trim();
            listType = new TypeToken<ArrayList<Car>>() {}.getType();
            //znaci hocemo arrayListu za car model
            gson = new Gson();
            ArrayList<Car> cars = gson.fromJson(carsJson, listType);

            for (Car c : cars) { //znaci mozemo da manipulisemo sa podacima
                System.out.println(c);

                return gson.fromJson(carsJson, listType);


//znaci u vracumo iz library gson biblioteku koju smo skinuli u lib direktorijum
            }

        }
        //inace za kada nema fajla
        //IO znc input output
        catch (IOException ex) {
            ex.printStackTrace();
        }


        return gson.fromJson(carsJson, listType);
    }}