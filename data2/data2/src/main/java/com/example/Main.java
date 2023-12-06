package com.example;

import com.mongodb.client.*;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.nio.file.Files.find;

public class Main {

    public static void main(String[] args) {

        /*    MongoClient  client =MongoClients.create("mongodb://localhost:27017");
            MongoDatabase  db = client.getDatabase("bookstore");
            MongoCollection<Document>authors=db.getCollection("authors");
            var result :FindIterable<Document> = authors.find();

            for(var rec: result){
                String firstName =(String) rec.get("first_name");
                System.out.println(firstName);
  }
            } znaci sa ovim smo poceli da radimo u javi a onda ono cuveno cut i paste...:)

            Drugi deo

            promenimo samo liniju koda MongoCollection<Document>authors =MongoDB.getCollection("authors");


            */



        try {
            /*ArrayList<Author>authors  =AuthorDAO.all();
            * authors.forEach(System.out::println);
            * //ovo ce da prodje kroz autore i da primeni println nad svakim autorom(format za brzo pisanje).
            *
            *   TRECI DEO
            *
            * Author author = new Author();
            * author.setFirstName("J.R.R");
            * author.setLastName("Tolkien");
            * author.setBorn(LocalDate.parse("1892-01-03"));
            * author.setDied(LocalDate.parse("1973-09-02"));
            * author.setBiography("""
            *
            *
            * John Ronald Reuel Tolkien: writer, artist, scholar, linguist.
            * Known to millions around the world as the author of The Lord of the Rings,
            * Tolkien spent most of his life teaching at the University of Oxford
            * where he was a distinguished academic in the fields of Old and Middle English
            *  and Old Norse.
            * His creativity, confined to his spare time, found its outlet in fantasy works
            * , stories for children, poetry, illustration and invented languages and alphabets...
            *
            * """);
            *
            * if(AuthorDAO.insert(author)){
            * System.out.prinln("Author saved");
            * }else{
            * System.err.println("Athor not saved!);
            * }
            *
            * */
            System.out.println(AuthorDAO.delete("6425b686643840d1fd09730b")); //znaci ovaj _id name je iz MongoDB compassa pa smo prekopirali
            //i na izlazu dobijemo ispis Agatha Christie...
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
