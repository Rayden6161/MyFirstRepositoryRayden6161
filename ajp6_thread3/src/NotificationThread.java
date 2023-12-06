import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class NotificationThread extends Thread{
    public LocalTime scheduled;
    public NotificationThread(LocalTime scheduled){
      this.scheduled = scheduled;
    }
    public void run(){
        LocalTime now = LocalTime.now();
        System.out.println("Current time "+ now);
        long delay = ChronoUnit.MILLIS.between(now,scheduled);
        if(delay<0){
            delay =86400000 + delay;  //znaci ovo smo napravili jer kad smo stavilli 9 sati vratilo
            //je timeout negative i zbog toga ide plus.A ovo je verovatno 24 sata ovaj broj e sad sekunde ili milisekunde.

        }
        try{Thread.sleep(delay);

        }catch(InterruptedException ex){
            throw new RuntimeException(ex);
        }
        System.out.println("You have a notification");



    }
}
