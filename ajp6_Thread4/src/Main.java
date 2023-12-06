public class Main {
    public static void main(String[] args) {
        CurrentTimeThread tNY = new CurrentTimeThread("America/New_York");
        tNY.start();
        CurrentTimeThread tL = new CurrentTimeThread("Europe/London");
        tL.start();
        CurrentTimeThread tB = new CurrentTimeThread("Asia/Shangai");
        tB.start();
        CurrentTimeThread tS = new CurrentTimeThread("Australia/Sydney");
        tS.start();


    }
}