import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeThread extends Thread{
    private DateTimeFormatter dtf =DateTimeFormatter.ofPattern("HH:mm:ss");

    public void run(){
     while(true)  { LocalTime now = LocalTime.now(); //morali smo da napravimo beskonacnu petlju i da kontrolisemo vreme izvrsavanja.
        System.out.println(now.format(dtf));
        try {
            Thread.sleep(1000); //ovo smo stavili jer je munjevito brzo ispisivao vreme u terminalu a ovako je na sekund podeseno
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }}


    }
}
