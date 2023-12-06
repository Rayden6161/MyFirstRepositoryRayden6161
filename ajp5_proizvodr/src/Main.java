import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        //znaci prosledim sve proizvode i onda nad njima odrdim filter

        ArrayList<Proizvod> proizvodi = new ArrayList<>();
        proizvodi.add(new Proizvod("Mleko", 179.99, "mlecni proizvodi"));
        proizvodi.add(new Proizvod("Cokoladno mleko", 69.99, "mlecni proizvodi"));
        proizvodi.add(new Proizvod("Hleb", 59.99, "peciva"));
        proizvodi.add(new Proizvod("Cokoladni kroasan", 899.99, "peciva"));
        proizvodi.add(new Proizvod("Coca-cola", 119.99, "gazirana pica"));
//znaci mi mozemo da napravimo niz lambda funkcija iliti Filter,nikako nam ne bi odgovaralo da ispisemo milion if-ova i pod if-ova,ovako je mnogo jedvostavnije i kompaktnije.
        Filter filterNaziv = (products, criteria) -> { //znaci ovo su nam lambde... imamo ih 3 za cenu,za...
            String query = (String) criteria.get("query"); //znaci imacemo neku vrstu upita od korisnika.
            ArrayList<Proizvod> filtered = new ArrayList<>();
            for (Proizvod p : products) {
                if (p.getNaziv().toLowerCase().contains(query.toLowerCase())) { //filter nam uvek radi isto jedino se ovaj deo uvek razlikuje
                    filtered.add(p);
                }
            }
            return filtered;
        };
        Filter filterKategorija = (products, criteria) -> {
            String query = (String) criteria.get("category");
            ArrayList<Proizvod> filtered = new ArrayList<>();
            for (Proizvod p : products) {
                if (p.getKategorija().toLowerCase().contains(query.toLowerCase())) {
                    filtered.add(p);
                    //OVI getteri u ovim Filterima su zapravo svojstva iliti private polja u klkasi Proizvod...
                }
            }
            return filtered;
        };

        //znaci mi dok nismo uvezli HashMap u kojoj mozemo da prosledimo vise tipova podatako,nismo mogli do tad da uvezemo ovaj drugi argument za cenu,nego samo za naziv...
        //znaci lambda mora da odgovara pozivu moje funkcije(znaci u Filter-u)
        //znaci nazvali smo ga  "criteria" jer nmz da se koristi  "args"(argument iz HashMape iz Filter interfejsa,zasto pa prokleti nelogicni program.)
        Filter filterCena = (products, criteria) -> {//morali smo proizvode rename-ovati u products jer smo u ArrayListi odredili naziv korisnici ili korisnik mrzi me da proveravam uglavnom skroz gore je
            double min = (double) criteria.get("min"); //sve ovo cemo i da primennimo za naziv
            double max = (double) criteria.get("max");//znaci zadali smo naziv min i max jer filtriramo na nekom sajtu pretragu na takav nacin
            //treba da castujemo u double...
            ArrayList<Proizvod> filtered = new ArrayList();
            /*znaci napravi novu listu,prodji kroz nju ,sve sto joj odgovara kriterijumima sacuvaj u njoj,vrati novu listu.
            * */
            for (Proizvod p : products) { //prodjemo kroz tu listu.
                if (p.getCena() >= min && p.getCena() <= max) {
                    filtered.add(p); //znaci ukoliko je ispunjen uslov vracamo filtrirane proizvode.
                }
                return filtered;
            }
            return proizvodi;
        };


//nije nam dozvolio isti name kao gore zato smo promenili naziv u products
        //inace nismo mogli gore da umetnemo args,nesto se bunio pa smo stavili   products.
/*za filterNaziv  i  filterKategorija filter kategoriju prosledjujemo String a za filterCena prosledjujemo 2 broja
 za minimalnu i maksimalnu cenu.Lambda mora da odgovara pozivu moje funkcije.Sad dodajemo u interfejs Hashmapu.
 Probali smo da addujemo 2 double promenljive (double min i double max) ali ja sad taj filter ne bi mogao da
 koristim za filterNaziv   i   filterKategoriju jer on prihvata samo jedan String od korisnika.
 Imamo mogucnost da prosledimo tip podatka koji moze da skladisti sta god ja hocu. HashMarp
 Znaci to cemo addovati u nas cuveni iterfejs
 Niko nam ne brani da napravimo Hashmap i da mu prosledimo double podatke a u ovoj drugoj situaciji da
 prosledim Hash koji ima String.

*/
        //simulacija
        proizvodi.forEach((p) -> System.out.println(p));
        //Kriterijum
        //korisnik bira kriterijume pa cemo napraviti HashMap-u...
        HashMap<String, Object> kriterijumi = new HashMap<>();
        ArrayList<Filter> filteri = new ArrayList<>();
        //izbori korisnika
//kriterijumi.put("query","coko");  //mogli smo staviti i druge pod komentarom,ovako nam je zamisljeni korisnik
        //odabrao sortiranje cena od 60 do 120...
// kriterijumi.put("min",60);
//kriterijumi.put("max",120);
        kriterijumi.put("category", "peciva"); //umetnemo u novokreiranu HashMapu kriterijume pretrage

        for (String key : kriterijumi.keySet()) { //prodjemo kroz kljuceve te HashMape
            if (key.equals(("query"))) {
                filteri.add(filterNaziv);  //i ukoliko je kljuc "query" onda novokreiranoj AL prosledi filterNaziv.
            }
            if (key.equals("min")) {
                filteri.add(filterCena);
            }
            if (key.equals("category")) {
                filteri.add(filterKategorija);
            }
        }
        System.out.println("\n\nFILTERI"); //da bi znali gde du nam filteri
        ArrayList<Proizvod> rezultat = proizvodi; //nas rezultat treba da budu proizvodi i zato smo uklonili new ArrayList
        for (Filter f : filteri) {
            rezultat = f.filter(proizvodi, kriterijumi);
        }
        rezultat.forEach((p) -> System.out.println(p));
    }


}


/*e ovako rezultat = f.filter(proizvodi,kriterijumi);} ovde ne mogu biti proizvodi zato u prvom ciklusu
izlistao nam je proizvode,onda u drugom kad smo promenili kriterijum opet nam je izlistao proizvode ali se
onaj kriterijum pre ovog sto smo promenili izbrisao sto ce reci da pamti samo jedan kriterijum
ZATO moramo promeniti   proizvodi u  rezultat
znaci uvek ce samo zadnji filter da bude primenjen.
znaci ovako rade oni sajtovi za pretragu,znaci filtriranje i ove je lambda korisna jako i ostale zavrzlame.

znaci napravimo odgovarajucu LAMBDU
napravimo polja koja su jedinstvena
prodjemo kroz polja i ako nam se nalaze u HashMap aktiviracemo Lambdu.


        */

//znaci napravili smo ArrayListu koja ce nam biti rezultat i u zavisnosti od filtera koje smo selektovali
        //onda smo prosli logicno kroz te filtere i onda cemo svaki taj filter da primenimo nad nasim
        //stackom proizovda i treba da dobijem na kraju isparsirani rezultat}
        // }


