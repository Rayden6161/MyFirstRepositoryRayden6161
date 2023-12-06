package com.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CarWriter {

public static boolean writeJSON(String jsonString, File f) throws IOException {
FileWriter fw = new FileWriter(f);
//mogu da nasledim FileWriter metodu i da je prosirimo da metoda write cuva objekte
fw.write(jsonString); //piso Json string
fw.close(); //i naravno zatvorimo metodu nakon pisanja
return true; //ako je sve dobro return true
//ova klasa sluzi da bi mogli da upisemo sadrzaj

}
public static boolean writeList(ArrayList<Car> cars, File f){ //dodatna metoda da snimimo listu automobila
    Gson gson = new Gson(); //treba da konvertujemo ovo u Json pa kreiramo gson objekat
  try {
      return CarWriter.writeJSON(gson.toJson(cars), f); //onda prosledjujemo konverziju u Json i nas fajl
//ako je sve dobro vratice true
  }
  catch(IOException ex){
      System.err.println(ex); //ovo nece da stopira aplikaciju ako nije uspesno ispisao
}
    return false;  //a ako nije dobro naravno vratice false
    }

}
