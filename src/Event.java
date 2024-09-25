import java.time.LocalDateTime;

public abstract class Event {
    String name;
    LocalDateTime dateTime;

    public  String getName()
    {
        return name;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int compareTo(Event e)
    {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
