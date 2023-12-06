package com.example.db;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AuthorDAO {

    private static Author createFromDoc(Document bsonAuthor) {
        //znaci kreiramo novog autora iz dokumenta(reda) u kolekciji(tabeli) authors
        Author author = new Author();
        author.set_id(bsonAuthor.getObjectId("_id"));
        author.setFirstName(bsonAuthor.getString("first_name"));
        author.setLastName(bsonAuthor.getString("last_name"));
        Date born = bsonAuthor.getDate("born");
        Date died = bsonAuthor.getDate("died"); //ovi keys-i su nam polja u nasoj kolekciji authors u MongoDB compass
        if(born != null) {//ako datum nije prazan
          author.setBorn(LocalDate.ofInstant(born.toInstant(), ZoneId.systemDefault()));
            //treba da konvertujemo u local instant...
        }
        if(died != null) { //ako datum smrti nije prazan
            author.setDied(LocalDate.ofInstant(died.toInstant(), ZoneId.systemDefault()));
            //treba da konvertujemo u local instant...
        }
        author.setPhoto(bsonAuthor.getString("photo"));
        author.setBiography(bsonAuthor.getString("biography"));
        return author;
    }

    public static ArrayList<Author> all() {
        ArrayList<Author> authors = new ArrayList<>();
        var result = MongoDB.collection("authors").find();
        for(var rec : result) {
            authors.add(createFromDoc(rec));
            //znaci ovim sve ono sto smo dobili u result prikazujemo na kraju u nasoj kolekciji authors...
        }
        return authors;
    }
    public static Author one(String objectId) { //ovo je onaj _id iz baze dugacki(String tipa jer je pod navodnicima)
        //znaci ovim pozivamo jednog autora,prosledimo metodi one() String _id-ja i dobijemo o autoru informacije
        ObjectId oid = new ObjectId(objectId);
        Document doc = MongoDB.collection("authors").find(Filters.eq("_id", oid)).first();
        if(doc != null) {
            //ukoliko taj dokument nije prazan vrati ovu metodu koju smo napravili iznad i posaljemo joj
            //dokument koji smo iznad kreirali... :)
            return createFromDoc(doc);
        }
        return null;
    }

    public static boolean insert(Author author) {
        Document doc = new Document();
        //ukoliko nije prazan jedan od dokumenata ispod onda dodajemo te dokumente author-u
        if(author.getFirstName() != null) doc.append("first_name", author.getFirstName());
        if(author.getLastName() != null) doc.append("last_name", author.getLastName());
        if(author.getBorn() != null) doc.append("born", author.getBorn());
        if(author.getDied() != null) doc.append("died", author.getDied());
        if(author.getPhoto() != null) doc.append("photo", author.getPhoto());
        if(author.getBiography() != null) doc.append("biography", author.getBiography());
        InsertOneResult result = MongoDB.collection("authors").insertOne(doc);
        ObjectId oid = result.getInsertedId().asObjectId().getValue();
        author.set_id(oid);
        return oid != null;
        //pa null jer mi nikada ne pravimo authors-a da ima polje id (ono mu se naknadno dodaje).
    }

    public static boolean delete(String objectId) {
        ObjectId oid = new ObjectId(objectId);
        return delete(oid);

        //vraca nam delete(metodu ispod) kojoj prosledjujemo ObjectId...
        //znaci poslali String ili ObjectId zapis bi trebao da nam bude indentican...
    }
    public static boolean delete(ObjectId objectId) {
        DeleteResult rs = MongoDB.collection("authors").deleteOne(Filters.eq("_id", objectId));
           //znaci brisemo "_id" i objectId mu prosledjujemo(to je onaj dugacki broj za id sto prekopiramo iz MongoDB compaasa).
        //ovo nam vraca DeleteResult
        return rs.getDeletedCount() > 0;


    }
    public static boolean update(Author author) {
        Bson bson = Updates.combine(//izkombinujemo updateove i to strpamo u bson objekat
                Updates.set("first_name", author.getFirstName()),
                Updates.set("last_name", author.getLastName()),
                Updates.set("born", author.getBorn()),
                Updates.set("died", author.getDied()),
                Updates.set("photo", author.getPhoto()),
                Updates.set("biography", author.getBiography())
                //addToSet dodaje jos jednu vrednost na vrednost a set je samo tu da promeni vrednost
                //znaci update-jemo polja koja imamo za autora
        );
        UpdateResult res = MongoDB.collection("authors").updateOne(
                Filters.eq("_id", author.get_id()), bson
        );
        return res.getModifiedCount() > 0;
    }
}
