import java.time.Duration;
import java.time.LocalDateTime;

public class Reminder extends Event{
    Duration timeBefore;
    Event event;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int compareTo(Event e) {
        return 0;
    }
}
