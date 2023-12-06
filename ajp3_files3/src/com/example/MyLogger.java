package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {
    private FileWriter fw; // koristimo da bi mogli koristiti fw ,da bi mogli dalogujemo podatke
    private File f;


 /*   public MyLogger()  throws IOException //ovaj exception da bi mogli formirati logger
    { //konstryktor gde prosledjujemo fajl i zelimo da napravimo lokalni fajl za log
        File err = new File("./errors.log"); //tu gde jesam napravi errors.log i onda napravi da postojim
        if(!err.exists()){ //proverimoda li postoji
            err.createNewFile(); //ako se desi ovaj izuzetak mn je error logger potreban da bi mogao da logujem podatke
    }fw =new FileWriter(err);
     f = err; //u slucaju da hocemo da zatvorimo fajl imamo naovaj nacin backup
  } //stavili smo pod komentar jer smo napravili jebeni MyLoggerFactory
*/
public MyLogger(File f)throws IOException{
if(!f.exists()){
f.createNewFile(); //e sad ako se desi ovaj
}

        fw =  new FileWriter(f); //ovo ce nam sluziti ako File f zatvorimo pa da moramo da otcorimo u nekom momentu
        this.f =f;
        }
public void log(Exception ex) throws IOException {
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y-M-d H:m:s");
  try {
      fw.write(String.format("%s - %s\n", dt.format(dtf), ex.getLocalizedMessage()));
      fw.close();
      System.out.println("Error logged!");
  }
  catch(IOException e){

  e.printStackTrace();

}

    }}


