import java.time.Duration;
import java.time.LocalDateTime;

public enum Urgency
{
    DISTANT,
    IMMINENT,
    OVERDUE;

    public static Duration thresholdOfImminence(){
        return null;
    }

    public void setThresholdOfImmience(LocalDateTime time)
    {

    }

    public static Urgency getUrgency(LocalDateTime time)
    {
        return null;
    }

}
