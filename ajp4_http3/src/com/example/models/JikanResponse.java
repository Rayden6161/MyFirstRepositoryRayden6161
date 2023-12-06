package com.example.models;

import java.util.ArrayList;

public class JikanResponse {
    private Object pagination;
    private ArrayList<Anime> data; //u ovu listu smo ubacili polja iz Anime klase

    public JikanResponse(){}

    public Object getPagination() {
        return pagination;
    } //ovde nam vraca paginaciju

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }

    public ArrayList<Anime> getData() {
        return data;
    } //hmmmm znaci mozemo da getujemo i setujemo AListe

    public void setData(ArrayList<Anime> data) {
        this.data = data;
    }
}
