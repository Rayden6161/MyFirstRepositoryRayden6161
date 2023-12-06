import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class CurrentTimeTask extends TimerTask {
private String timezone;
private ZoneId zone;
private DateTimeFormatter dtf;
public CurrentTimeTask(String timezone) {

    this.timezone = timezone;
    this.zone = ZoneId.of(timezone);

}
    @Override//sad kad smo uzeli tri komponente iznad sad mozemo u run da uzmemo vreme
    public void run() {
    LocalTime now =LocalTime.now(zone);
    System.out.printf("%s:%s\n",timezone,now.format(dtf));


    }

    }

