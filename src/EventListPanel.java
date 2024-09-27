import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.comparing;

public class EventListPanel extends JPanel {
    ArrayList<Event> events;
    JPanel controlPanel;
    JPanel displayPanel;
    JPanel titlePanel;
    JComboBox sortDropDown;
    JCheckBox filterDisplay;
    JButton addEventButton;

    JLabel name, startTime, endTime, duration, location,  completionStatus;

    final int LABEL_WIDTH = 210;
    final int LABEL_HEIGHT = 30;

    public EventListPanel()
    {
        this.events = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(EventPlanner.WINDOW_WIDTH, EventPlanner.WINDOW_HEIGHT));

        controlPanel = new JPanel();
        controlPanel.setMaximumSize(new Dimension(EventPlanner.WINDOW_WIDTH, 100));
        filterDisplay = new JCheckBox();
        addEventButton = new JButton("Add Event");
        sortDropDown = new JComboBox();

        // options for Jcombobox
        sortDropDown.addItem("Name");
        sortDropDown.addItem("Date");
        sortDropDown.addItem("Name-Reversed");
        sortDropDown.addItem("Date-Reversed");
        sortDropDown.addActionListener(al -> {
            String selection = (String) sortDropDown.getSelectedItem();

            // based on selection, use appropriate sorting algorithm
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

        // panel to hold the title jlabels
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setMaximumSize(new Dimension(EventPlanner.WINDOW_WIDTH, 70));

        // display panel to hold the EventPanels
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setOpaque(false);
        displayPanel.setBackground(Color.yellow);

        // Control panel to hold the controls
        controlPanel.add(addEventButton);
        controlPanel.add(sortDropDown);

        // create title jlabels, set sizes, and set fonts
        name = new JLabel("Name");
        name.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        name.setFont( new Font("Serif",Font.PLAIN, 20));

        startTime = new JLabel("Start Time");
        startTime.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        startTime.setFont( new Font("Serif",Font.PLAIN, 20));

        endTime = new JLabel("End Time");
        endTime.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        endTime.setFont( new Font("Serif",Font.PLAIN, 20));

        duration = new JLabel("Duration");
        duration.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        duration.setFont( new Font("Serif",Font.PLAIN, 20));

        location = new JLabel("Location");
        location.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        location.setFont( new Font("Serif",Font.PLAIN, 20));

        completionStatus= new JLabel("Status");
        completionStatus.setPreferredSize( new Dimension (LABEL_WIDTH , LABEL_HEIGHT));
        completionStatus.setFont( new Font("Serif",Font.PLAIN, 20));

        // action lister for button
        addEventButton.addActionListener(al ->
        {
            // on click, create new AddEventModal dialog box
            AddEventModal model = new AddEventModal();
            model.setVisible(true);
            Event e;

            // if the AddEventModal window is closed, check if form is submitted
            if (model.isSubmitted())
            {
                e = model.inputFromUser(); // get the form input
                events.add(e); // add new event
                displayPanel.removeAll();
                displayEvents();
                revalidate();
                repaint();
            }
        });


        // add components to titlePanel
        addComponents(titlePanel, name, startTime, endTime, duration, location, completionStatus);
        // add components to this EventListPanel
        addComponents(this, controlPanel,titlePanel,displayPanel);

        // sample events
        events.add(new Meeting("Project #2", LocalDateTime.now(), LocalDateTime.now(), "Library Room 215"));
        events.add(new Meeting("Safety Meeting", LocalDateTime.now(), LocalDateTime.now(), "Training Room"));
        events.add(new Meeting("Study Group", LocalDateTime.now(), LocalDateTime.now(), "Library Room 117"));
        events.add(new Deadline("Project #2", LocalDateTime.now()));


        displayEvents();  // create the EventPanels
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
            displayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        revalidate();
        repaint();
    }

    private void addComponents(JPanel panel, Component... comp)
    {
        for (Component c: comp)
        {
            panel.add(c);
        }
    }

}
