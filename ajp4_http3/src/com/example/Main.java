package com.example;

import com.example.http.AplicationError;
import com.example.http.HTTPClient;
import com.example.http.HTTPError;
import com.example.models.Anime;
import com.example.models.JikanResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            String JikanJson = HTTPClient.get("https://api.jikan.moe/v4/anime?q=Naruto");

            //u nas string smestamo HTTPClient klasu i njenu metodu get kojoj prosledjujemo url; koji je inace argument u klasi
            //sad moramo da dodamo gson iz library
            Gson gson = new Gson();
 JikanResponse response =gson.fromJson(JikanJson,JikanResponse.class); //inastanci prosledjujemo String pomocu kojeg se klijent
            //nakacio na link i ocekujemo odgovor  koji smestamo dobijamo iz JikanResponse klase i smestamo ga u response
            //pretvaramo iz Json-a i prosledjujemo metodi String za konekciju i JikanResponse klasu sa svim njenim svojstvima...
 for(Anime a: response.getData()){//prodjemo kroz listu Anime(klasa) tj odgovor koji smo da kazem zahtevamo getujemo iz liste.
     //Na kraju samo isprintamo.
     System.out.println(a);
 }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AplicationError e) {
            e.printStackTrace();
        } catch (HTTPError e) {
            System.out.println("Error code "+ e.getStatusCode());
        }

    }
}