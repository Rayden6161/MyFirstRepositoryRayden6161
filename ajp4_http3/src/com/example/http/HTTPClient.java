package com.example.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HTTPClient {
    public static String get(String url) throws URISyntaxException, IOException, InterruptedException, AplicationError,HTTPError {
        try{ //poslali smo nas argument url
        HttpRequest request = HttpRequest.newBuilder(new URI(url)).GET().timeout(Duration.of(30, ChronoUnit.SECONDS)).build();
        /*saljemo request tako sto saljemo newBuilder metodi od HttpRequest-a
        * new URI i njemu prosledimo nas argument da ga parsira . GET() metodi u nastavku dodelimo
        * i timeout metodu da saljemo request na odredjeni period vremena i to sve zavrsrimo
        * sa build()... */
        HttpClient client = HttpClient.newHttpClient();//ovo nam pravi klijenta i onda treba da formiramo  response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        /*znaci nas response je zapravo kad klijent salje zahtev a ocekuje response stringova.*/
        if(response.statusCode() >=400 & response.statusCode()<500){
            throw new HTTPError(response.statusCode(),url); //vraca nam status code i nas argument
        }
        else if(response.statusCode()>=500){
            throw new HTTPError (response.statusCode(),"service not aviable");
        }
        return response.body(); //vraca nam response sa njegovom metodom body();
    }
        catch(URISyntaxException|IOException|InterruptedException ex){
throw new AplicationError(ex.getMessage()); //ukoliko pukne bilo koji za URL onda kazemo ovo
        }

}}
