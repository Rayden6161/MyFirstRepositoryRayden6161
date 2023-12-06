package com.example;

import java.util.Arrays;

public class Osoba {
    private String ime;
    private String prezime;
    private int godine;
    private Adresa adresa;
    private String[] hobiji;

    public Osoba() {}

    public Osoba(String ime, String prezime, int godine, Adresa adresa, String[] hobiji) {
        this.ime = ime;
        this.prezime = prezime;
        this.godine = godine;
        this.adresa = adresa;
        this.hobiji = hobiji;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String[] getHobiji() {
        return hobiji;
    }

    public void setHobiji(String[] hobiji) {
        this.hobiji = hobiji;
    }

    public String toString() {
        return String.format("""
                Ime: %s
                Prezime: %s
                Godine: %d
                Adresa:
                    Ulica: %s
                    Broj: %d
                    Grad: %s
                Hobiji: %s""", ime, prezime, godine,
                adresa.getUlica(), adresa.getBroj(), adresa.getGrad(),
                Arrays.toString(hobiji));

        //znaci samostalno smo kreirali toString() jer imamo dosta polja
        //takodje ovi get za adresa su tu jer su u Adresa privatna polja...
    }
}
