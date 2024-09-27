import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton;
    JLabel name, startTime, endTime, duration, location, completion;

    public EventPanel()
    {
        this.setLayout(new GridLayout(1, 7));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setMaximumSize(new Dimension(EventPlanner.WINDOW_WIDTH, 30));
        completeButton = new JButton("Complete");
        // action listener for complete button
        completeButton.addActionListener(al ->
        {
            // when button is clicked, change test to completed and change color to gray
            completion.setText("Completed");
            setBackground(Color.LIGHT_GRAY);
            if(event instanceof Meeting meetingEvent)
            {
                meetingEvent.complete();
            }
            revalidate();
            repaint();
        });
        name = new JLabel();
        startTime = new JLabel();
        endTime = new JLabel();
        duration = new JLabel();
        location = new JLabel();
        completion = new JLabel();
        setLabelsFont(name, startTime, endTime, duration, location, completion);
        addComponents(name, startTime, endTime, duration, location, completion, completeButton);
       revalidate();
       repaint();
    }

    // draw the event panel labels
    public void draw()
    {
        name.setText(event.getName());
        if (event instanceof Meeting meetingEvent) //check if event is a meeting
        {
            startTime.setText(meetingEvent.getDateTime().format(DateTimeFormatter.ofPattern("MM/dd/YYYY  HH:mm")));
            endTime.setText(meetingEvent.getDateTime().format(DateTimeFormatter.ofPattern("MM/dd/YYYY  HH:mm")));

            Duration dur = meetingEvent.getDuration();
            duration.setText(String.format("%02d:%02d",
                    dur.toHours(),
                    dur.toMinutesPart()));
            location.setText(meetingEvent.getLocation());
            setEventComplete(meetingEvent);

        }
        else if (event instanceof Deadline deadline) // check if event is a deadline
        {
            LocalDateTime now = LocalDateTime.now();
            Duration dur = Duration.between(now, deadline.getDateTime());
            startTime.setText(now.format(DateTimeFormatter.ofPattern("MM/dd/YYYY  HH:mm")));
            endTime.setText(deadline.getDateTime().format(DateTimeFormatter.ofPattern("MM/dd/YYYY  HH:mm")));
            duration.setText(String.format("%02d:%02d",
                    dur.toHours(),
                    dur.toMinutesPart()));
            location.setText("N/A");
            setEventComplete(deadline);
        }
    }


    public void setEvent(Event e)
    {
        event = e;
    }

    public Event getEvent()
    {
        return event;
    }

    public void setLabelsFont(JLabel...labels)
    {
        for(JLabel label: labels)
        {
            label.setFont(new Font("Serif", Font.PLAIN, 20));
        }
    }


    private void addComponents(Component... comp)
    {
        for (Component c: comp)
        {
            add(c);
        }
    }

    private void setEventComplete(Completable comp)
    {
        String complete;
        if (comp.isComplete())
        {
            complete = "Completed";
            setBackground(Color.lightGray);
        }
        else
        {
            complete = "Incomplete";
        }
        completion.setText(complete);
    }
}
