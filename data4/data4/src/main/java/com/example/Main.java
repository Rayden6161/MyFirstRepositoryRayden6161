package com.example;

import com.example.dao.AuthorDAO;
import com.example.models.Author;
import org.jdbi.v3.core.Jdbi;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        /*try {
            Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/book_reviews", "Jadmin", "1");
            //znaci pre nego smo pozvali createTable(jdbi); morali smo napraviti bazu podataka bookstore u phpMyAdmin-u
            //ovo su metode u ovoj klasi skroz ispod
            //createTable(jdbi);

            //createAuthors(jdbi);
            //znaci ova metoda nam pravi sve Authors-e tj ubacuje ih u bazu(one objekte u create Authors() metodi...)
            //printAllAuthors(jdbi);
            //printAuthor(jdbi);
            //updateAuthor(jdbi);
            deleteAuthor(jdbi, 3);
        } catch(Exception ex) {
            ex.printStackTrace();
        }*/

        try {
            /*ArrayList<Author> authors = AuthorDAO.all();
            for(var author : authors) {
                System.out.printf("%s %s\n", author.getFirst_name(), author.getLast_name());
            }*/
            /*Author newAuthor = new Author("J.R.R.", "Tolkien", LocalDate.of(1892,1,3),
                    LocalDate.of(1973,9,2));
            System.out.println(AuthorDAO.save(newAuthor));*/

            Author authorToUpdate = AuthorDAO.one(4);
            authorToUpdate.setPhoto(fileToBase64(System.getenv("JAVA_RESOURCES") + "/jdbi_Dog.png")); //ovo je putanja do fajla

            System.out.println(AuthorDAO.update(authorToUpdate));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createTable(Jdbi jdbi) {
        //koristimo useHandle jer ne ocekujemo nazad nikakve rezultate
        //ispod u zagradi je lambda objekat i koristimo handle objekat
        jdbi.useHandle( handle -> {

            handle.execute("""
                    CREATE TABLE authors (
                        author_id INT(5) AUTO_INCREMENT,
                        first_name VARCHAR(120) NOT NULL,
                        middle_name VARCHAR(200),
                        last_name VARCHAR(120) NOT NULL,
                        born DATE,
                        died DATE,
                        photo MEDIUMBLOB,
                        biography MEDIUMTEXT,
                        PRIMARY KEY(author_id)
                    )ENGINE=INNODB;""");
        });
    }

    public static byte[] fileToBase64(String file_path) {
        //znaci ovu metodu pozivamo kao argument u jednom ad autora
        //znaci ovaj format Base64 ima neke veze sa slikama,znaci fajl pretvaramo u taj format
        try {
            File f = new File(file_path);
            //putanju koju unesemo do fajla
            byte[] fb = Files.readAllBytes(f.toPath());
            //Files.readAllBytes() bi bila najbolja metoda za citanje bajtova
            //moramo da enkodujemo zato smo spakovali u byte[]fb promenljivu...
            return Base64.getEncoder().encode(fb);
            //znaci ovo vracamo nazad ne mora fb.toString() jer vracamo nazad niz bajtova.
            //e sad tek enkodujemo byte[]fb ,znaci u nasoj bazi cuvamo photo polje kao niz bajtova.
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void createAuthors(Jdbi jdbi) {
        Author agathaChristie = new Author("Agatha", "Christie", LocalDate.of(1890,9,15), LocalDate.of(1976,1,12));
        Author danSimmons = new Author("Dan", "Simmons", LocalDate.of(1947,4,4),
                fileToBase64(System.getenv("JAVA_RESOURCES") + "/jdbi_dan_simmons.webp"), """
                Dan Simmons grew up in various cities and small towns in the Midwest, including Brimfield, Illinois, which was the source of his fictional "Elm Haven" in 1991's SUMMER OF NIGHT and 2002's A WINTER HAUNTING. Dan received a B.A. in English from Wabash College in 1970, winning a national Phi Beta Kappa Award during his senior year for excellence in fiction, journalism and art.
                                
                Dan received his Masters in Education from Washington University in St. Louis in 1971. He then worked in elementary education for 18 years—2 years in Missouri, 2 years in Buffalo, New York—one year as a specially trained BOCES "resource teacher" and another as a sixth-grade teacher—and 14 years in Colorado.""");
        int raf = jdbi.withHandle(handle -> {
            //napravli smo String insertString sa gotovim upitom i prosledimo dole metodi samo naziv promenljive ...
            String insertString = """ 
                   INSERT INTO authors 
                   VALUES(NULL, :first_name, :middle_name, :last_name, :born, :died, :photo, :biography);
                   """;
            //znaci prvo ide   :     pa nazive za polja u nasoj KLASI  Author.

            int rows_affected = handle.createUpdate(insertString).bindBean(agathaChristie).execute();
            //bindBean() znaci moramo reci sa kim povezujemo klasu.
            rows_affected += handle.createUpdate(insertString).bindBean(danSimmons).execute();
            //execute() koristtimo kad NEMAMO BEAN
            //znaci na rows_affected dodajemo  sve redom...

           return rows_affected;
        });
        System.out.println("Authors added: " + raf);
        //ovde ispod napisemo koliko je autora dodato
    }

    public static void printAllAuthors(Jdbi jdbi) {
        //znaci trazi nam da uradimo kastovanje jer je default list(imamo krajnju metodu tj
        //vraca nam naseg autora u LISTI!
        ArrayList<Author> authors = (ArrayList<Author>) jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM authors;").mapToBean(Author.class).list();
            //znaci iz Autora cu dobiti listu
        });
        for(Author author : authors) {
            System.out.println(String.format("%s %s", author.getFirst_name(),
                    author.getLast_name()));
            //PRODJEMO kroz autore i istampamo imena i prezimena autora.
        }
    }


    public static Author getAuthor(Jdbi jdbi, int author_id) {
        //takodje ovu metodu pozivamo u metodi ispod printAuthor() i prosledimo jdbi i broj id-a kao argumente.
        //ovde nam vraca autora po tom id-ju valjda..
        //ovde nije void nego Author jer hocemo da vratimo Author-a
        return jdbi.withHandle( handle -> {
            return handle.createQuery("SELECT * FROM authors WHERE author_id = :author_id")
                    .bind("author_id", author_id).mapToBean(Author.class).first();
            //OVDE NEMA execute() jer ga koristimo kad nemamo bean a ovde imamo bean (Author.class) i zato ide first()
            //znaci ovde prosledjujemo samo jedan podatak i mogu da kazem umesto bindToBean() samo bind()...
            //i onda pojedinacno navedem podatak ili podatke,imamo jedan podatak a to je author_id,vracamo nazad Author-a...
        });
    }

    public static void printAuthor(Jdbi jdbi) {

        //takodje ovu metodu getAuthor() pozivamo u metodi ispod printAuthor() i prosledimo jdbi i broj id-a kao argumente.
        Author author = getAuthor(jdbi, 1); //dodjemo do autora
        System.out.println(String.format("%s %s", author.getFirst_name(), author.getLast_name()));
    }

    public static void updateAuthor(Jdbi jdbi) {
        //opet i ovde koristimo metodu getAuthor()
        //takodje pozivamo i metodu fileToBase64 u nasem konstruktoru za tu sliku/e
        Author agathaChristie = getAuthor(jdbi, 1);
        agathaChristie.setPhoto(fileToBase64(System.getenv("JAVA_RESOURCES") + "/jdbi_agatha.png"));
        agathaChristie.setBiography("""
                Dame Agatha Mary Clarissa Christie is the best-selling author of all time. She wrote 66 crime novels and story collections, fourteen plays, and six novels under a pseudonym in Romance. Her books have sold over a billion copies in the English language and a billion in translation. According to Index Translationum, she remains the most-translated individual author, having been translated into at least 103 languages. She is the creator of two of the most enduring figures in crime literature-Hercule Poirot and Miss Jane Marple-and author of The Mousetrap, the longest-running play in the history of modern theatre.
                                
                Agatha Mary Clarissa Miller was born in Torquay, Devon, England, U.K., as the youngest of three. The Millers had two other children: Margaret Frary Miller (1879–1950), called Madge, who was eleven years Agatha's senior, and Louis Montant Miller (1880–1929), called Monty, ten years older than Agatha.""");
        int raf = jdbi.withHandle(handle -> {
           return handle.createUpdate("""
                   UPDATE authors SET photo = :photo, biography = :biography 
                   WHERE author_id = :author_id;""").bindBean(agathaChristie).execute();
        });
        System.out.println( raf > 0 ? "Author updated" : "Error updating author" ); //ternarni operator ako je vece prvo u suprotnom drugo
        //znaci u int raf ide koliko smo kolona izmenili(ocekujemo minimum jednu)...
    }

    public static void deleteAuthor(Jdbi jdbi, int author_id) {
        int raf = jdbi.withHandle( handle -> {
           return handle.createUpdate("DELETE FROM authors WHERE author_id = :author_id")
                   .bind("author_id", author_id).execute();
        });
        System.out.println(raf > 0 ? "Author deleted" : "Error deleting author");
    }
}
//ZNACI PRVA POLOVINA LEKCIJE JE BILA SVE OVO U MAINU,ZNACI TO JE PROSTIJI NACIN,OSNOVE,KAKO GOD
//SAD U DRUGOJ POLOVINI LEKCIJE BICE MALO PROMENA...:)