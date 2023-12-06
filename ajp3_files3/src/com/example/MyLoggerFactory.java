package com.example;

import java.io.File;
import java.io.IOException;

public class MyLoggerFactory
{
    public static MyLogger logger; //pravimo ovu staticku promenljivu da bi smo je zvali dole
    public static  MyLogger create(){

    File f = new File("./errors.log");
    return MyLoggerFactory.create(f); //ovde se     racuna da sam prosledio fajl
    }
    public static MyLogger create(File f) { //ovde nam treba try catch blok
        try {
            MyLoggerFactory.logger = new MyLogger(f);
            return MyLoggerFactory.logger;


        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null; //moramo da imamo i null da java ne bi bacala gresku
    }

}
