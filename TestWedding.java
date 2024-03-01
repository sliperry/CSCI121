import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TestWedding extends JFrame implements ActionListener {

    // Constants for the frame dimensions
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 300;

    // Labels for One of the Couple's
    JLabel coupleOneFNLabel = new JLabel("1st Couple's Name:");
    JLabel coupleOneLNLabel = new JLabel("(First Name), (Last Name)");


    // Panels to organize the One of the Couple's labels and text fields
    JPanel coupleOneNameLblPanel = new JPanel();
    JPanel coupleOneNameTxtPanel = new JPanel();

    // Text Fields for One of the Couple's First and Last Name
    JTextField coupleOneFNTextField = new JTextField();
    JTextField coupleOneLNTextField = new JTextField();

    // Labels for One of the Couple
    JLabel coupleTwoFNLabel = new JLabel("2nd Couple's Name:");
    JLabel coupleTwoLNLabel = new JLabel("(First Name), (Last Name)");

    // Panels to organize One of the Couple's labels and text fields
    JPanel coupleTwoNameLblPanel = new JPanel();
    JPanel coupleTwoNameTxtPanel = new JPanel();

    // Text Fields for One of the Couple
    JTextField coupleTwoFNTextField = new JTextField();
    JTextField coupleTwoLNTextField = new JTextField();

    // Labels for Date and Location
    JLabel dateLabel = new JLabel("Wedding Date (YYYY-MM-DD):");
    JLabel locationLabel = new JLabel("Location:");

    // Text Fields for Date and Location
    JTextField dateTextField = new JTextField();
    JTextField locationTextField = new JTextField();

    // Button to submit the form
    JButton submitButton = new JButton("Submit");


    public TestWedding() {
        super("Wedding Planner");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Organize and add One of the Couples' labels and text fields to the frame
        coupleOneNameLblPanel.add(coupleOneFNLabel);
        coupleOneNameLblPanel.add(coupleOneLNLabel);
        coupleOneNameLblPanel.setLayout(new GridLayout(1, 2));
        add(coupleOneNameLblPanel);
        coupleOneNameTxtPanel.add(coupleOneFNTextField);
        coupleOneNameTxtPanel.add(coupleOneLNTextField);
        coupleOneNameTxtPanel.setLayout(new GridLayout(1, 2));
        add(coupleOneNameTxtPanel);

        // Organize and add the Other Couple's labels and text fields to the frame
        coupleTwoNameLblPanel.add(coupleTwoFNLabel);
        coupleTwoNameLblPanel.add(coupleTwoLNLabel);
        coupleTwoNameLblPanel.setLayout(new GridLayout(1, 2));
        add(coupleTwoNameLblPanel);
        coupleTwoNameTxtPanel.add(coupleTwoFNTextField);
        coupleTwoNameTxtPanel.add(coupleTwoLNTextField);
        coupleTwoNameTxtPanel.setLayout(new GridLayout(1, 2));
        add(coupleTwoNameTxtPanel);

        // Add Date and Location labels and text fields to the frame
        add(dateLabel);
        add(dateTextField);
        add(locationLabel);
        add(locationTextField);

        // Add an empty label for spacing and the Submit button to the frame
        add(new JLabel());
        add(submitButton);

        // Add ActionListener to the Submit button
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Get user input
        String coupleOneFirstName = coupleOneFNTextField.getText();
        String coupleOneLastName = coupleOneLNTextField.getText();
        String coupleTwoFirstName = coupleTwoFNTextField.getText();
        String coupleTwoLastName = coupleTwoLNTextField.getText();
        String weddingDateString = dateTextField.getText();
        String location = locationTextField.getText();

        // Create Couple objects
        Couple couple = new Couple(new Person(coupleOneFirstName, coupleOneLastName),
                new Person(coupleTwoFirstName, coupleTwoLastName));

        // Parse wedding date
        LocalDate weddingDate = LocalDate.parse(weddingDateString);

        // Create Wedding object
        Wedding wedding = new Wedding(couple, weddingDate, location);

        // Display the details in a dialog
        JOptionPane.showMessageDialog(this, "Wedding Details:\n\n"
                + "Couple One: " + wedding.getCouple().getPerson1().getFirstName()+ " "
                    + wedding.getCouple().getPerson1().getLastName() + "\n"
                + "Couple Two: " + wedding.getCouple().getPerson2().getFirstName()+ " "
                    + wedding.getCouple().getPerson2().getLastName() + "\n"
                + "Wedding Date: " + wedding.getWeddingDate() + "\n"
                + "Location: " + wedding.getLocation());
    }

    public static void main(String[] args) {
        TestWedding example = new TestWedding();
        example.setVisible(true);
    }
}
