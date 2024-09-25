import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Event implements Completable{
    boolean complete;


    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
