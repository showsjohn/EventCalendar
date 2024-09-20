import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable{
    ArrayList<Reminder> reminders;

    public void addReminder(int daysBefore, int hoursBefore, int minutesBefore)
    {

    }
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

    @Override
    public void complete() {

    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
