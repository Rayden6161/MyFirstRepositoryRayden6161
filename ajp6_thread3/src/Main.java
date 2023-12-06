import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalTime scheduled =LocalTime.parse("18:40:50");
        //treba da oduzmem vreme od 24h nazad i da dobijem koliki je delay
        //cifre zelene u zagradi da je podeseno recimo  9 sati vratilo bi gresku timeout negative
        NotificationThread t =new NotificationThread(scheduled);
        t.start();
    }
}