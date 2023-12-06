package com.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
/*Csv je tip tekstualnog dokumenta jednostavan za te tabele(koristili smo prvo JSON sac CSV
* koji je microsoftov standard.*/
public class CSVParser implements OsobaParser {
    @Override
    public Osoba parse(String path) {
        try {
            /*Ovde vec radimo da isparsiramo podatke iz .csv  dokumenta a pre toga smo podatke
            * iz .json samo prebacili i modifikovali kako odgovara u csv...*/

            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            /*znc ovim parsiramo ono sto smo uneli kao putanju a to je .csv fajl koji je prethodno
            * obradjen iz .json fajla.*/
            for(CSVRecord r : records) {
                //onda prodjemo kroz sve to i uzmemo sve podatke koje nam trebaju za tu osobu

                String ime = r.get(0);
                /*Da nas fajl ima prvi red sa imenima polja onda bi koristili r.get("ime");
                * A sa obzirom da nas fajl nema to koristimo indexe jer znam koje mi je sta po redu
                * Znaci ovo su nam pozicije u .csv dok koje su razdvojene zarezom*/
                String prezime = r.get(1);
                String godine = r.get(2);
                String ulica = r.get(3);
                String broj = r.get(4);
                String grad = r.get(5);
                String hobiji = r.get(6);

                //znaci na osnovu ovoga treba da formiram osobu,znaci prvo cu da kreiram adresu

                Adresa adresa = new Adresa(ulica, Integer.parseInt(broj),grad);
                //ovde ide Integer.parseInt(broj) jer nam je broj String tipa a to je iznad
                Osoba osoba = new Osoba(ime, prezime, Integer.parseInt(godine),
                        adresa, hobiji.split(";"));
                //posto su nam hobiji niz splitujemo ga sa ;...
                return osoba; //i ovakvu osobu na kraju vracemo nazad
                //ovaj return ce da zaustavi petlju i onda ce da kaze ejj posle ove petlje tj ne konta da u petlji ima return
                //i kaze ne vraces mi vrednost nazad a ja mu stvarno vracem return.Zato opet ide dole return null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null; //ovaj null ovde je full back
    }
}
