public class Main {
    public static void main(String[] args) {
/*CurrentTimeThread t1 =new CurrentTimeThread();
t1.start();*/

         //za razliku od Timera gde nam schedula metoda radi za nas imamo da odredimo delay ovde
        //toga nema...Da bi ovo radilo kako zelimo imamo opciju da napravimo beskonacnu nit za
        //svaku sekundu sto nama treba a imamo i opciju da se nit zavrsava kad se zavrsi kod
        //da bi ovo radilo mi moramo da napravimo beskonacnu while petlju.
CurrentTimeThread t1 = new CurrentTimeThread();
t1.start();


    }
}