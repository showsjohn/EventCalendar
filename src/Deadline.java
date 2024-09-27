import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable{
    boolean complete;

    public Deadline(String name, LocalDateTime deadline)
    {
        this.name = name;
        this.dateTime = deadline;
    }

    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }


}
