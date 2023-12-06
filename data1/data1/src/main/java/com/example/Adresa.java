package com.example;

public class Adresa {
    /*znaci u tekstualnom file-u(JSON ekstenzija kad smo ga snimili)umetnuli smo
    * objekat Adresa i u njega niz jer je slozen objekat a u niz smo stavili ova polja
    * Takodje mogu u projektnom folderu pronaci json fajl i steci uvid kako se kreira isti ...*/
    private String ulica;
    private int broj;
    private String grad;

    public Adresa() {}

    public Adresa(String ulica, int broj, String grad) {
        this.ulica = ulica;
        this.broj = broj;
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}
