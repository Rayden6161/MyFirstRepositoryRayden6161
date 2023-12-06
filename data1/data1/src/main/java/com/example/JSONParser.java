package com.example;

import com.google.gson.Gson;

import java.io.File;
import java.nio.file.Files;

public class JSONParser implements OsobaParser {
    @Override
    public Osoba parse(String path) {
        Gson gson = new Gson();
        File f = new File(path);
        try {
            return gson.fromJson(Files.readString(f.toPath()), Osoba.class);
            //znaci moramo da ucitamo sadrzaj
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
