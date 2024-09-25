import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meeting extends Event implements Completable{
   LocalDateTime endDateTime;
   String location;
   boolean complete;

   public Meeting(String name, LocalDateTime dateTime, LocalDateTime endDateTime, String location )
   {
        this.name = name;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
        this.location = location;
   }


    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    // specific methods to Meeting class
    public LocalDateTime getEndTime()
    {
        return endDateTime;
    }

    public Duration getDuration()
    {
        return Duration.between(this.dateTime, endDateTime);
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
    public void setEndTime(LocalDateTime end)
    {
        this.endDateTime = end;
    }


}
