import javax.swing.*;
import java.awt.*;

public class EventPlanner {
    public final static int WINDOW_WIDTH = 1400;
    public final static int WINDOW_HEIGHT = 1000;
    private static final int SIDE_PADDING = 100;

    public static void main(String[] args) {
        // create new jframe and add EventListPanel
        JFrame jframe = new JFrame("Event Planner");
        jframe.setLayout(new BorderLayout());
        jframe.setSize( new Dimension(WINDOW_WIDTH+SIDE_PADDING, WINDOW_HEIGHT));
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        EventListPanel eventList = new EventListPanel();
        jframe.add(eventList);
        jframe.setVisible(true);
    }
}
