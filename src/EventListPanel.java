import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.comparing;

public class EventListPanel extends JPanel {
    ArrayList<Event> events;
    ArrayList<EventPanel> eventPanels;
    JPanel controlPanel;
    JPanel displayPanel;
    JPanel titlePanel;
    JComboBox sortDropDown;
    JCheckBox filterDisplay;
    JButton addEventButton;

    JLabel name, time, duration, location,  completionStatus;

    final int LABEL_WIDTH = 180;
    final int LABEL_HEIGHT = 30;

    public EventListPanel()
    {
        this.events = new ArrayList<>();
        eventPanels = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(1200, 1000));

        controlPanel = new JPanel();
        controlPanel.setMaximumSize(new Dimension(1200, 100));
        filterDisplay = new JCheckBox();
        addEventButton = new JButton("Add Event");
        sortDropDown = new JComboBox();

        sortDropDown.addItem("Name");
        sortDropDown.addItem("Date");
        sortDropDown.addItem("Name-Reversed");
        sortDropDown.addItem("Date-Reversed");
        sortDropDown.addActionListener(al -> {
            String selection = (String) sortDropDown.getSelectedItem();

            switch (selection)
            {
                case "Name":
                {
                    events.sort(comparing(event-> event.getName().toUpperCase()));
                    break;
                }
                case "Date":
                {
                    events.sort(comparing(Event::getDateTime));
                    break;
                }
                case "Name-Reversed":
                {
                    events.sort(comparing((Event event) -> event.getName().toUpperCase()).reversed());
                    break;
                }
                case "Date-Reversed":
                {
                    events.sort(comparing(Event::getDateTime).reversed());
                    break;
                }
                default:
                    throw new RuntimeException("Invalid menu item");
            }
            displayEvents();
        });

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setMaximumSize(new Dimension(1000, 70));

        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(10, 1));
        displayPanel.setOpaque(false);
        displayPanel.setBackground(Color.yellow);


        controlPanel.add(filterDisplay);
        controlPanel.add(addEventButton);
        controlPanel.add(sortDropDown);

        name = new JLabel("Name");
        name.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        name.setFont( new Font("Serif",Font.PLAIN, 20));
        time = new JLabel("Time");
        time.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        time.setFont( new Font("Serif",Font.PLAIN, 20));
        duration = new JLabel("Duration");
        duration.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        duration.setFont( new Font("Serif",Font.PLAIN, 20));
        location = new JLabel("Location");
        location.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        location.setFont( new Font("Serif",Font.PLAIN, 20));
        completionStatus= new JLabel("Status");
        completionStatus.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        completionStatus.setFont( new Font("Serif",Font.PLAIN, 20));

        addEventButton.addActionListener(al ->
        {
            AddEventModel model = new AddEventModel();
            model.setVisible(true);
            Event e = null;

            System.out.println("going");
            if (model.isSubmitted())
            {
                e = model.inputFromUser();
                events.add(e);
                displayPanel.removeAll();
                displayEvents();
                revalidate();
                repaint();
            }


        });


        titlePanel.add(name);
        titlePanel.add(time);
        titlePanel.add(duration);
        titlePanel.add(location);
        titlePanel.add(completionStatus);
        titlePanel.setMaximumSize(new Dimension(1000, 50));

        this.add(controlPanel);
        this.add(titlePanel);
        this.add(displayPanel);

        events.add(new Meeting("cik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("tik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("aik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("zik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("cik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("tik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));
        events.add(new Meeting("aik tok", LocalDateTime.now(), LocalDateTime.now(), "room"));

        displayEvents();
        this.revalidate();
        this.repaint();

    }

    private void displayEvents()
    {
        displayPanel.removeAll();

        for (Event e : events)
        {
            EventPanel ePanel = new EventPanel();
            ePanel.setEvent(e);
            ePanel.draw();
            displayPanel.add(ePanel);
        }
        revalidate();
        repaint();
    }



/*  private void addEventPanels()
    {
        for (Event e : events)
        {
            EventPanel ePanel = new EventPanel();
            ePanel.setEvent(e);
            eventPanels.add(ePanel);
        }
    }

    private void displayEvents()
    {
        displayPanel.removeAll();
        for (EventPanel ePanel : eventPanels)
        {
            ePanel.draw();
            displayPanel.add(ePanel);
        }
        revalidate();
        repaint();
    }*/
}
