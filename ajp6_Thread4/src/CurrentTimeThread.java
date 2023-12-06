import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CurrentTimeThread extends Thread{
    private String timezone;
    private ZoneId zone;
    private DateTimeFormatter dtf;
    public CurrentTimeThread(String timezone){
        this.timezone = timezone; //Ovde smo stavili kao argument samo Strin timezone
        //zato sto u mainu navodimo za koju zonu hocemo da pokrenemo run
        this.zone = ZoneId.of(timezone);
         this.dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    }
    public void run(){
        while(true){
            LocalTime now = LocalTime.now(zone);
            System.out.printf("%s:%s\n",timezone,now.format(dtf));
            //Europe/London:11:29:12  Jedan od ispisa u konzoli,znaci prvi karakter : nam razdvaja
            //nasu zonu i trenutno vreme u njoj i zato je u printf timezone i trenutno vreme.
            try{
                Thread.sleep(1000);

            }catch(InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
