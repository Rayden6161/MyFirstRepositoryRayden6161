import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
      Joiner joiner =(words, separator) -> { //nije potrebno za atribute dodavati tip podatka
          //instanci naseg Interfejsa (prosledjujemo njene parametre bez naglasavanja tipova i ide jednako)
          //Znaci ovo je lambda koliko se secam ima veze sa jednim funckcionalnim interfejsom a to je Joiner
          String joined = "";
          for(String word: words){
              joined +=word+separator; //u nas String smestamo rec i separator (argumenti interfejsa)
          }
          return joined; //i vrati taj string...
    };
      //znaci ovo su parametri iz donje metode...
      print(new String[]{"Ann", "Mark", "Roger"},";",joiner); //prebacili smo iz zagrada ova imena
    //da bi se isprinatla a dole smo u konstruktoru j stavili parametre Joiner klase...
//argumenti su naravno niz Stringova,separator i nas interfejs Joiner iliti njegova instanca koju smo obradili gore
        ArrayList<String>names =new ArrayList<>();
        names.add("Marcus");
        names.add("James");
        names.add("Elizabeth");
        names .add("Michaella");

      //  names.forEach((name)-> System.out.println(name));
        names.stream().map((name)->name.toUpperCase()).map((name)->"*" + name + "*")
                .collect(Collectors.toList())//mapiramo da nam se izmedju imena pojavljuje separator *
                .forEach((name)-> System.out.println(name));//znaci sad smo dobili listu sa
        //i na kraju isprintamo ta imena.
        //imenima sa velikim slovima
        // mapa dozvoljava da udjemo u neki niz i da ga obradimo,metode,razni nacini..


}
public static void print(String[]words,String separator,Joiner j){ //maltene imamo rec,separator i interfejs kojem prosledjujemo njegovu metodu join sa njenim parametrima
    String joined= j.join(words, separator); //smestimo u string metodu naseg interfejsa

    System.out.println(joined); //i onda to istampamo...
    System.out.println();



}

}
//korisnik kaze hocu kriterijum za stavku proizvodjaca proizvoda,ja kazem evo ti lambda za proizvode
//korisnik kaze hocu za cenu ja kazem evo ti lambda za cenu i onda pustimo filtere
//  ZA NJEGOVE PARAMETRE ZNACI TO JE MAGIJA LAMBDE.
