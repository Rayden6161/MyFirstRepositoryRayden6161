package com.example;

import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
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
        Date died = bsonAuthor.getDate("died");
        if(born != null) {
          author.setBorn(LocalDate.ofInstant(born.toInstant(), ZoneId.systemDefault()));
          //treba da konvertujemo u local instant...
        }
        if(died != null) {
            author.setDied(LocalDate.ofInstant(died.toInstant(), ZoneId.systemDefault()));
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
    public static Author one(String objectId) {
        ObjectId oid = new ObjectId(objectId);
        Document doc = MongoDB.collection("authors").find(Filters.eq("_id", oid)).first();
        //znaci ovo ce da nam vrati Document(red) verovatno _id ili sta vec nisam siguran
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
}
