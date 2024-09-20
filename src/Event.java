import java.time.LocalDateTime;

public abstract class Event {
    String name;
    LocalDateTime dateTime;

    public abstract String getName();
    public abstract LocalDateTime getDateTime();
    public abstract void setDateTime(LocalDateTime dateTime);
    public abstract void setName(String name);
    public abstract int compareTo(Event e);
}
