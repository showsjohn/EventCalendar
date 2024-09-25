import javax.swing.*;
import java.awt.*;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setLayout(null);
        jframe.setSize( new Dimension(1200,800));
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        EventListPanel eventList = new EventListPanel();
        jframe.add(eventList);
        jframe.setVisible(true);
    }

    public static void addDefaultEvents(EventPanel events)
    {

    }
}
