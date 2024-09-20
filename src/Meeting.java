import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meeting extends Event implements Completable{
   LocalDateTime endDateTime;
   String location;
   ArrayList<Reminder> reminders;

    public void addReminder(int daysBefore, int hoursBefore, int minutesBefore)
    {

    }

    @Override
    public void complete() {

    }

    @Override
    public boolean isComplete() {
        return false;
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

    // specific methods to Meeting class
    public LocalDateTime getEndTime()
    {
        return null;
    }

    public int getDuration()
    {
        return 0;
    }

    public String getLocation()
    {
        return "";
    }

    public void setLocation(String location)
    {

    }
    public void setEndTime()
    {

    }


}
