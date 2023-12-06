package com.example;

import static spark.Spark.*;

import com.example.dao.JdbiConnection;
import com.example.utils.IniParser;
import com.example.web.routes.*;
import com.example.web.templates.ThymeleafTemplateEngine;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File iniFile = new File(System.getenv("DATA5_INI")); //znaci ovo ce biti lokacija naseg ini fajla
            System.out.println(iniFile.getAbsolutePath());
            IniParser.getInstance().parse(iniFile); //ovo je singleton
            //Ovaj IniParser  i njegovu metodu getInstance() smo napravili da bi smo olaksali
            //da ne moramo praviti konfiguracioni fajl kao prosli put pa onda bi morali morali praviti
            //novi objekat koji bi prosledjivali svakoj klasi.
            //Za razliku od toga ovaj objekat je globalni,znaci kad god mi treba ini objekat ja cu da
            //pozovem IniParser.getInstance() i onda pozovemo parse(iniFile)da nam cita konfiguracioni fajl

            staticFiles.externalLocation(IniParser.getInstance().getValue("static/static_files"));
            //znaci kad je ucitao od gore ini fajl imacemo sekciju static i kljuceve static_files
            ThymeleafTemplateEngine engine =
                    new ThymeleafTemplateEngine(IniParser.getInstance().getValue("static/templates"),
                            //ISTO TAKO IDE I ZA templates i extension kao kod static_files iznad
                            //znaci sve sto hocemo da promenimo menjamo u okviru ini fajla

                            IniParser.getInstance().getValue("static/static_extension"));

            get("/", new AuthorDashboardRoute(), engine);
            get("/author/new", new NewAuthorFormRoute(), engine);
            post("/author/new", new NewAuthorSubmitRoute(), engine);
            get("/author/delete/:author_id", new AuthorDeleteRoute(), engine);
            get("/author/edit/:author_id", new EditAuthorFormRoute(), engine);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}