package com.example;

import org.apache.commons.configuration2.INIConfiguration;

import java.io.FileReader;
import java.io.Reader;

public class INIParser implements OsobaParser {
    @Override
    public Osoba parse(String path) {
        //ovo nam je parser koji iz ini fajla prikazuje ovde vrednosti koje su nam potrebne iliti ih parsira.
        INIConfiguration configuration = new INIConfiguration();
        try {
            Reader reader = new FileReader(path);
            configuration.read(reader);

            String ime = configuration.getSection("osnovni").getProperty("ime").toString();
            String prezime = configuration.getSection("osnovni").getProperty("prezime").toString();
            String godine = configuration.getSection("osnovni").getProperty("godine").toString();
            String ulica = configuration.getSection("adresa").getProperty("ulica").toString();
            String broj = configuration.getSection("adresa").getProperty("broj").toString();
            String grad = configuration.getSection("adresa").getProperty("grad").toString();
            String hobiji = configuration.getSection("hobiji").getProperty("hobiji").toString();

            Adresa adresa = new Adresa(ulica, Integer.parseInt(broj),grad);
            Osoba osoba = new Osoba(ime, prezime, Integer.parseInt(godine),
                    adresa, hobiji.split(","));//ovde ide ","  jer u ini ; se tretira kao komentar
            return osoba;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
