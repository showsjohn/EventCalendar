import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog
{
    private enum Type {MEETING, DEADLINE}
    public Boolean isSubmitted = false;

    Type type;
    JLabel labelName, labelStartTime, labelEndTime, labelLocation, title;
    JTextField name, startTime, endTime, location;
    JButton submit;
    JCheckBox meetingType, deadlineType;
    JPanel datePicker;
    JComboBox month, day, year;

    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 600;

    final int TEXTFIELD_WIDTH = WINDOW_WIDTH - (int)(WINDOW_WIDTH * 0.1);
    final int TEXTFIELD_HEIGHT = 50;

    final int LABEL_WIDTH = 220;
    final int LABEL_HEIGHT = 25;

    public AddEventModal()
    {
        this.setTitle("Add Event");
        this.setModal(true);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setLayout(null);
        title = new JLabel("Add Event");
        title.setBounds((WINDOW_WIDTH / 2) - (LABEL_WIDTH / 4), LABEL_HEIGHT , LABEL_WIDTH, LABEL_HEIGHT);

        datePicker = new JPanel();
        labelName = new JLabel("Name");
        labelStartTime = new JLabel("Start Time (Meeting only)");
        labelEndTime = new JLabel("End Time");
        labelLocation = new JLabel("Location (Meeting only");

        name = new JTextField();
        name.setFont(new Font("Serif", Font.PLAIN, 20));
        startTime = new JTextField("yyyy-MM-dd HH:mm");
        startTime.setFont(new Font("Serif", Font.PLAIN, 20));
        endTime = new JTextField("yyyy-MM-dd HH:mm");
        endTime.setFont(new Font("Serif", Font.PLAIN, 20));
        location = new JTextField();
        location.setFont(new Font("Serif", Font.PLAIN, 20));

        meetingType = new JCheckBox("Meeting");
        meetingType.setBounds(WINDOW_WIDTH / 2 - 150, WINDOW_HEIGHT - 250, 150, 50);
        meetingType.addActionListener(al ->
        {
            type = Type.MEETING;
        });
        deadlineType = new JCheckBox("Deadline");
        deadlineType.addActionListener(al ->
        {
            type = Type.DEADLINE;
        });
        deadlineType.setBounds(WINDOW_WIDTH / 2 + 50, WINDOW_HEIGHT - 250, 150, 50);

        submit = new JButton("Submit");
        submit.setBounds((WINDOW_WIDTH / 2) - 50,WINDOW_HEIGHT - 120, 100, 40);

        addLabelComponents(labelName, labelStartTime, labelEndTime, labelLocation);
        addFieldComponents(name, startTime, endTime, location);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(meetingType);
        buttonGroup.add(deadlineType);
        this.add(meetingType);
        this.add(deadlineType);
        this.add(submit);
        this.add(title);

        submit.addActionListener(al ->
        {
            isSubmitted = true;
            this.dispose();
        });
    }

    public boolean isSubmitted() { return isSubmitted; }

    public Event inputFromUser()
    {
        // get input from form fields
        String Name = name.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime EndTime = LocalDateTime.parse(endTime.getText(), formatter);

        // if type is of Meeting, get the location and startTime
        if(type == Type.MEETING)
        {
            String Location = location.getText();
            LocalDateTime StartTime = LocalDateTime.parse(startTime.getText(), formatter);
            return new Meeting(Name, StartTime, EndTime, Location);
        }
         else
        {
            return new Deadline(Name, EndTime);
        }
    }

    // helper methods for setting up components
    public void addFieldComponents(Component... comp)
    {
        int offSet = 1;

        for(Component c: comp)
        {
            c.setBounds(10, (TEXTFIELD_HEIGHT + LABEL_HEIGHT) * offSet, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
            this.add(c);
            offSet++;
        }
    }

    public void addLabelComponents(Component... comp)
    {
        int offSet = 1;
        for(Component c: comp)
        {
            c.setBounds(10, ((TEXTFIELD_HEIGHT + LABEL_HEIGHT) * offSet) - LABEL_HEIGHT - 10, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
            this.add(c);
            offSet++;
        }
    }

}
