package com.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//znaci ovde pozivamo svoj loger u main pre svih nasih resursa
        File logFile =new File  (System.getenv("Java_resources" + "/ajp3_errors.log"));
        MyLoggerFactory.create(logFile);
        //staticka metoda imamo ArrayListu automobila
       File f = new File(System.getenv("Java_resources") + "/ajp3_cars.json"); //link ka fajlu
       //znaci citamo iz ovog fajla koji smo kreirali preko mokaroo-a
        ArrayList<Car>cars = new ArrayList();

        try{
            cars=CarFactory.fromFile(f); //ocekujemo da ce popuniti u ovom koraku listu

        }
        catch(IOException ex){ //jedini error koji mogu da imama je da ne mogu da otvorim ovaj json fajl
            //System.err.println("Fatal" + ex );//umesto ovog kreiracemo logger
         try{  //File logFile =new File  (System.getenv("Java_resources" + "/ajp3_errors.log"));

            MyLogger logger = new MyLogger(logFile);
            logger.log(ex);
            System.exit(1);

        }
        catch(IOException e){ //za svaki catch moram da koristim novi objekat za logovanje
             //u tom slucaju mn treba novaklasa MyLoggerFactory
             e.printStackTrace(); //sad za terstiranje ovog moramo da stavimo donji pasus pod komentarom
        }
         System.exit(1);
        }

        /*
        Car c = new Car (51,"Ford","Fiesta",2016,8000); //probamo da kreiramo neki auto
        //i da ga dodamo u Json fajl
        cars.add(c);
        boolean saved = CarWriter.writeList(cars,f);  //onda nase automobile snimimo i prosledimo cars i fajl gde snimamo

        if(saved){
            System.out.println("Car has been saved");

        }
else{
            System.err.println("Error while saving car");
        }
*/

    }
}