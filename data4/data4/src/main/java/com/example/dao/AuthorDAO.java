package com.example.dao;

import com.example.models.Author;

import java.util.ArrayList;
//znaci ova polja sa karakterom ":" name se odnose na polja u nasemo modelu Author.class...
//author_id = :author_id

//   :author_id  se odnosi na polje u model klasi Author.class
public class AuthorDAO {
    //HANDLE nam je objekat za konekciju  i sluzi nam za sve vrste upita i da radi sam menadzment transakcije
    //ima 2 metode withHandle() ->> vraca rezultat   useHandle() => valjda ne vraca rezultat
    //koristicemo i lambdu koja ce da radi odredjene stvarcice.

    public static ArrayList<Author> all() {
        return (ArrayList<Author>)JdbiConnection.get().withHandle( handle -> {
           return handle.createQuery("SELECT * FROM authors;").mapToBean(Author.class).list();
           ////list()         vraca listu  rezultata upita
            //createQuery()   prosledjuje Java Bean klasu na koju se mapira rezultat
            //mapToBean()     prosledjuje Java Bean klasu na koju se mapira rezultat.
        });
    }
    //ovo je pretraga po id-u iz autora(kolekcije)...
    public static Author one(int author_id) {
        //vrati iz klase metodu get() sa hendlom koji nam vraca upit koji je vezan sa author_id-jem(znaci ono sto smo uneli kao argument u mainu.).
        return JdbiConnection.get().withHandle( handle -> {
            return handle.createQuery("SELECT * FROM authors WHERE author_id = :author_id").
                    bind("author_id", author_id).mapToBean(Author.class).first();
            //first()  vraca prvi rezultat upita
            //bind()   kod pripremljenih upita veze parametre sa upitom
            //createQuery () kreiranje upita koji vracaju ResultSet kao rezultat.

        });
    }

    //Unosimo autora
    public static boolean save(Author author) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
           return handle.createUpdate("INSERT INTO authors VALUES (NULL, :first_name, :middle_name, :last_name, :born, :died, :photo, :biography)")
                   .bindBean(author).execute();
            //bindBean veze parametre za POJO
           //ovde imamo createUpdate() umest createQuery() zato je malo drugacija sintaksa
        });
        return rows_affected > 0;//ide boolean jer imamo uslov da bi vratilo
    }
    //createUpdate()   kreiranje upita koji vracaju nemericku vrednost
    //bindBean veze parametre za POJO
    //exexute()  izvrsava bilo koju vrstu upita

    public static boolean update(Author author) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
            return handle.createUpdate("""
                    UPDATE authors SET
                    first_name = :first_name, middle_name = :middle_name, 
                    last_name = :last_name, born = :born, died = :died,
                    photo = :photo, biography = :biography 
                    WHERE author_id = :author_id""")
                    .bindBean(author).execute();
            //znaci kad imamo  :   misli se na vrednost znc author_id(polje u tabeli) jednako je vrednost u klasi koju navedemo...


            //createUpdate()   kreiranje upita koji vracaju nemericku vrednost
            //bindBean veze parametre za POJO
            //znaci ovo ima veze sa MongoDB compassom zato je malo drugacije nego sa MySql-om,i zato imamo  :   ...


        });
        return rows_affected > 0; //ide boolean jer imamo uslov da bi vratilo
        //znaci vrati da nam je unos pozitivan iliti update(znaci kad pozovemo tu metodu.
    }

    public static boolean delete(int author_id) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
           return handle.createUpdate("DELETE FROM authors WHERE author_id = :author_id")
                   .bind("author_id", author_id).execute();
           //bind() kod pripremljenih upita veze parametre sa upitom
            //createUpdate()   kreiranje upita koji vracaju nemericku vrednost
            //execute() izvrsava bilo koju vrstu upita.
        });
        return rows_affected > 0;//ide boolean jer imamo uslov da bi vratilo
    }


    public static ArrayList<Author> livingAuthors() {
        return (ArrayList<Author>)JdbiConnection.get().withHandle( handle -> {
           return handle.createQuery("SELECT * FROM authors WHERE died IS NULL").
           mapToBean(Author.class).list();
           //list() vraca listu  rezultata upita
            //createQuery()   prosledjuje Java Bean klasu na koju se mapira rezultat
            //mapToBean()     prosledjuje Java Bean klasu na koju se mapira rezultat.
        });
    }

}
