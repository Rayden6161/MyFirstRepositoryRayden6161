public class Main {
    public static void main(String[] args) {
     String text = "John.Davidson/Belgrade Michael.Barton/Krakow Ivan.Perkinson/Moscow";
     Person [] persons = new Person[3];
     int personsCounter = 0; // napravili smo proizvoljnu promenuljivu
        //nju koristimo za popunjavanje niza PERSONS
    String []usersData= text.split("[./]");

    for(int i = 0;  i <  usersData.length;  i+=3){
        //znaci ovo ispod se sve desava u for petlji koja pripada nizu userData

Person p = new Person(usersData[ i ],usersData[i+1],usersData[i+2] );
//ZNACI IZ NIZA STRINGOVA usersData prebacimo Stringove u konstruktor iznad zato smo  i napravili niz Stringova.
//znaci imamo u Person klasi 3 svojstva,u jednoj iteraciji ubacimo npr John Davidson Belgrade itd...
        //znaci u jednoj iteraciji preskace brojac 3 reci a one su odvojene zbog splitovanja iznad
     //userdata splitovani niz smo prosledili nasoj p insanci Person klase tj njenom konstruktoru
     //Imamo 3 elementa u jednoj iteraciji jer unutar Person imamo 3 svojstva...

//neophodno je da podatke iz Person niza smestim u Person niz gore
persons[personsCounter]=p; //personsCounter koristimo zapopunjavanje  niza persons
       //izjednacavamo glavni niz sa ovim sto smo ovde uradili sa p,znaci to treba
       //prebaciti u gornji glavni niz.
        //znaci cim smo izjednacili sa p onda ide kroz niz od nultog indeksa
        //i onda povecavamo za 1 tu iteraciju ili valjda prolaz
        //znaci u konstuktor glavnog niza umetnemo  I-ti  clan niza,dok je u for petlji odredjena iteracija 3 mesta unapred

        personsCounter ++; //kad to sve izjednacimo tj kad resimo da u glavni niz ubacimo
        //sve elementa niza onda povecavamo personsCounter ++; jer smo ionako ovu promenljivu
        //inicijalizovali tj pomocu nje cemo popuniti glavni niz.


    }
    for(Person person: persons){ /*
     Izjednacili smo nasu instancu p sa novim nizom i glavnim ujednom i zato ovde navodimo
     klasu Person pa naziv i konacno ime naseg glavnog niza...
     znaci poistovetili smo nasu klasu sa nasim nizom u koraku iznad ...

     */
        System.out.println(person.getInfo());
    }

    }
}