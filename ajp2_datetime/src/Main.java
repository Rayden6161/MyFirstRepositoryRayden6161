import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {


    ZoneId myZone = ZoneId.systemDefault();
    //za dobijanje vremenske zone po default metodi
        System.out.println("My zone" + myZone);

        Clock clock = Clock.systemDefaultZone();

        System.out.println(" Instant timestamp " + clock.instant());

        Instant instantNow = Instant.now(); //now(metoda) je za prikazivanje vremena pomocu instance
        Instant instantBefore = Instant.parse("2022-12-14T12:00:00.0Z");
        System.out.println("Before :"+ instantBefore.isBefore(instantNow));

        //vraca nam poruku u ispis da li je instant before zaista pre instatNow i ukoliko jeste
        //vraca vrednost boolean tipa true a ukoliko nije vraca false.
        System.out.println("Until :" +instantBefore.until(instantNow, ChronoUnit.HOURS));
        /*vraca nam broj proteklih sati koji su protekli izmedju instant before(znace vremena koje
        * smo napisali u zagradi iznad a mora da se poklapa sa formaterom String i instantNow)
        * Znaci vraca broj proteklih sati. */


        LocalDate dateJapan = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Date in Japan " + dateJapan);

        LocalDate parsedDate = LocalDate.parse("2022-10-18");
        //parsirali smo string koji odgovara formatu datuma

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d.M.y");
        System.out.println("Formated date " + dateJapan.format(fmt));
        //ovaj fmt je verovatno format koji smo kreirali u DateTimeFormatter klasi
        //Inace DateTimeFormatter se isto nalazi u Time klasi iliti je njen podpaket
        //sluzi da formatiram kako ce da mi se prikaze datum
        //imamo u tabeli prikaz svake metode pa tako i ovde ova slova predstavljaju dan mesec i godinu

        LocalTime currentTime = LocalTime.now();

        LocalTime belgradeTime = LocalTime.now(ZoneId.of("Europe/Belgrade"));
        System.out.println("current time : " + currentTime);
        System.out.println("Belgrade time :" + belgradeTime);

        System.out.println("Chrono diff " +ChronoUnit.DAYS.between(parsedDate,dateJapan));
        //ovde pravimo razliku sa ChronoUnit i to u danima
        //unutra su nam parametri LocalDateTime tipa podataka





    }
}