package com.example;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;

public class YAMLParser implements OsobaParser {
    @Override
    public Osoba parse(String path) {
        Yaml yaml = new Yaml();
        try {
            FileInputStream fs = new FileInputStream(path);
            Map<String, Object> data = yaml.load(fs);
            //Object jer imamo razlicite objekte
            String ime = data.get("ime").toString();
            String prezime = data.get("prezime").toString();
            int godine = (int) data.get("godine");
            Map<String, Object> mapAdresa = (Map<String, Object>) data.get("adresa");
            //Ovde imamo Map Stringova i Object-a jer je slozeni objekat( imamo nekoliko polja)...
            String ulica = mapAdresa.get("ulica").toString();
            int broj = (int) mapAdresa.get("broj");
            String grad = mapAdresa.get("grad").toString();
            //znaci ulica broj i grad su nam polja   slozenog objekta Adresa...:)
            ArrayList<String> hobijiList = (ArrayList<String>) data.get("hobiji");
            //dodjemo do hobija(navedeni su unutar niza)
            String[] hobiji = new String[hobijiList.size()];
            hobiji = hobijiList.toArray(hobiji);
            Adresa adresa = new Adresa(ulica,broj,grad);
            Osoba osoba = new Osoba(ime, prezime,godine,
                    adresa, hobiji);
            return osoba;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
