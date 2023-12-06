package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
 File f = new File (System.getenv("Java_resources")+ "/ajp3_test.txt");
 try{
    if( !f.exists() ) {
        f.createNewFile ();    }

    } catch (IOException e) {
     e.printStackTrace();
     System.exit(1);
 }
 try {
     FileWriter fw = new FileWriter(f,true); //navedemo true da se ne bi overwtite-vao sadrzaj preko ,znaci
     //ako bi izostavili iz parametara true onda bi pri svakom menjanju stringa ispod zamenio se tekst a ovako ce
     //se nadovezati sa true kao parametrom
     fw.write(" Treci sadrzaj");
     fw.close(); //znaci morali smo da zatvorimo ovu metodu inace u kreiranom fajlu nije htelo da ispise sadrzaj
     //koji smo uneli

 }
 catch(IOException e){ //input output exception,u slucaju da ne moze da pronadje fajl
     e.printStackTrace();
     System.exit(1);
 }
    }
 }
