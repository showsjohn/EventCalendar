import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton;
    JLabel name, time, duration, location, completion;

    public EventPanel()
    {
        this.setLayout(new GridLayout(1, 6));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        completeButton = new JButton("Complete");
        completeButton.addActionListener(al ->
        {
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
        time = new JLabel();
        duration = new JLabel();
        location = new JLabel();
        completion = new JLabel();
        setLabelsFont(name, time, duration, location, completion);
        addComponents(name, time, duration, location, completion, completeButton);
       revalidate();
       repaint();
    }

    public void draw()
    {
        if (event instanceof Meeting meetingEvent)
        {
            System.out.println("meeting event");

            name.setText(meetingEvent.getName());
            time.setText(meetingEvent.getDateTime().format(DateTimeFormatter.ofPattern("MM/dd/YYYY  HH:mm")));
            Duration dur = meetingEvent.getDuration();
            duration.setText(String.format("%02d:%02d",
                    dur.toHours(),
                    dur.toMinutesPart()));
            location.setText(meetingEvent.getLocation());

            String complete;
            if (meetingEvent.isComplete())
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
        else {
            System.out.println("not a meeting");

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
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    public void updateUrgency()
    {

    }

    private void addComponents(Component... comp)
    {
        for (Component c: comp)
        {
            add(c);
        }
    }
}
