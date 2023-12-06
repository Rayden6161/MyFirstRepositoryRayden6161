package com.example.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoDB {
    /*znaci u Mongo imamo bazu,unutar nje kolekciju(tabelu) i dokument(red)...
     * relacije se mogu uspostavljati    */
    private static MongoDatabase db;

    public static MongoDatabase get() {
        if(db == null) {
            MongoClient client = MongoClients.create("mongodb://localhost:27017"); //aha ovde se povezujemo sa bazom u MongoDB compass
            db = client.getDatabase("bookstore");
            //znaci nakon ove metode get() u mainu ispisemo modifikacije pod Drugi deo...
        }
        return db; //OVIM vracamo nasu bookstore databazu...
    }

    public static MongoCollection<Document> collection(String name) {
        return get().getCollection(name);
    }
    //ovim vracamo kolekciju a pritom prosledimo naziv kao parametar...znaci ukucamo "authors" kad pozovemo
    //metodu collection("authors") i kontam vraca kolekciju authors iz nase baze u MongoDB compassu.
}
