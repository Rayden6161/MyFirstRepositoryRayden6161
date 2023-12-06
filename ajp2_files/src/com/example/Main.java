package com.example;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*File f = new File("D:\\Java_projects\\ajp2_files\\src\\com\\example\\raven.txt");
        System.out.println("File exists  " +f.exists()); */   //ovo je kad prosledimo full putanju

       // File f = new File("src/com.example/raven.txt");
        //relative path unutar paketa

  //  String filePath = Main.class.getResource("raven.txt").getPath(); //trazimo resurs u main iliti nas txt
        //ima kao sto vidimo vise nacina da importujemo txt u klasi
       File f = new File(System.getenv("Java_resources") + "/raven.txt");
        System.out.println("File exist " + f.exists());

try{
    Scanner s = new Scanner(f); //prosledili smo fajl skeneru
   /* s.useDelimiter("\\Z");
    System.out.println(s.next())      Ceo fajl odjednom cita ;*/



    //ovo nam je pattern hocu da procitam ceo tekt unutar fajla
    //\\n bi bio pattern da hoci da citam po jedan red...
    /*fajl nam je samo link ka datoteci ali ne mora da znaci da je link validan u ovom slucaju kad smo
    fajl prosledili skeneru u ispisu se pojavio Exception pa smo kreiraali zato try catch metodu
    * */
     //citanje datoteke a NextLine je citanje sa tastature

    int brojReda = 0;
    int brRaven= 0; //posle ovoga idemo u while
    s.useDelimiter("\\n");
         while(s.hasNext())//ako moze da procita sadrzaj idemo po redu
         {String red =s.next().trim(); //citanje datoteke i uklanjanje praznih spaceova .,,
             brojReda++; //kako koji red procitamo tako se povecava logicno i broj redova...
             System.out.printf("[%d] %s\n",brojReda,red);
             //isprintaj broj redova i ovo drugo je ovaj String...
             if(red.toLowerCase().contains("raven")){ //Ukoliko pronadje raven povecavaj broj    brRaven++;...
                 brRaven++;
             }

         }
    System.out.println("Broj pojavljivanja reci raven je : " + brRaven);
}
catch(Exception ex){
    System.err.println("File : \"" +f.getAbsolutePath() +"\"doesnt exist" ); //\"   je za navodnike
}



//System.out.println("File exists  " + f.exists());


/*znaci u windows smo ukucali  env u search i onda odredili putanju koju smo nazvali Java_resources
inace getenv je metoda klase System.
//znaci izasli smo pa usli iz sistema da bi prepoznao ove promene unutar enviroment variables
koje smo uneli u gornjim koracima
* */

}
}