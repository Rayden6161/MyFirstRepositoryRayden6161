package com.example.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class Base64Utils {
    public static String fromBase64Bytes(byte[] base64Bytes) {
        return Base64.getEncoder().encodeToString(base64Bytes);
    }

    public static byte[] bytesToBase64Bytes(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }
//ovo je nova funkcija koja prati enkovovani String i ekoduje bajtove u Base64 string da
    //bi posle prikazali slike.
    public static byte[] fileToBase64(String file_path) {
        //znaci konvertujemo fajl(unesemo putanju do njega) u Base64 da prikazemo sliku u niz tih bajtova
        try {
            File f = new File(file_path);
            byte[] fb = Files.readAllBytes(f.toPath());
            return Base64.getEncoder().encode(fb);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}