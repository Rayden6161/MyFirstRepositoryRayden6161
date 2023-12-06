import java.util.Timer;

public class Main {
    public static void main(String[] args) {
       Timer tw = new Timer();
        Timer tL = new Timer();
        Timer tB = new Timer();
        Timer tS = new Timer();

       tw.scheduleAtFixedRate(new CurrentTimeTask("America/New_York"),0,1000);
        tL.scheduleAtFixedRate(new CurrentTimeTask("Europe/London"),0,1000);
        tB.scheduleAtFixedRate(new CurrentTimeTask("Asia/Shangai"),0,1000);
        tS.scheduleAtFixedRate(new CurrentTimeTask("Australia/Sydney"),0,1000);
    }
}