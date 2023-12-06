package com.example.http;

public class AplicationError extends Exception{ //ovo nam je za Exception klasa tj nasledjuje gresku
    public AplicationError(String message) {
        super(message);
    }
    //u konstruktor prosledimo poruku i pozovemo super kao roditeljsku klasu(Exception jer ga nasa klasa nasledjuje)
    //i pored Exceptiona kojeg nasledjujemo prosledjujemo i nas message.

    //znaci ova klasa nam sluzi za greske u aplikaciji
}
