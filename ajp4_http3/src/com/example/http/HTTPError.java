package com.example.http;

public class HTTPError extends Exception {//nasledjujemo gresku
    private int statusCode;
    private String message;
    public  HTTPError(int statusCode){
        this.statusCode=statusCode;
    }
    public HTTPError(int statusCode,String message){
        this.statusCode=statusCode; //u konstruktoru ubacimo statusCode(int) znaci da nam vrati broj statusa greske
        this.message= message; //ovo nam vraca poruku od te greske
    }
//ova klasa nam sluzi da nasledimo sve greske koje se desavaju na HTTP,verovatno server.
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
