import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestWedding extends JFrame implements ActionListener {

    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 400;

    private JTextField coupleOneFNTextField = new JTextField(20);
    private JTextField coupleOneLNTextField = new JTextField(20);
    private JTextField coupleTwoFNTextField = new JTextField(20);
    private JTextField coupleTwoLNTextField = new JTextField(20);
    private JTextField dateTextField = new JTextField(20);
    private JTextField locationTextField = new JTextField(20);

    private JButton submitButton = new JButton("Submit");

    public TestWedding() {
        super("Wedding Planner");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        mainPanel.add(createInputPanel("1st Couple's Name:", coupleOneFNTextField, coupleOneLNTextField), gbc);

        gbc.gridy++;
        mainPanel.add(createInputPanel("2nd Couple's Name:", coupleTwoFNTextField, coupleTwoLNTextField), gbc);

        gbc.gridy++;
        mainPanel.add(createInputPanel("Wedding Date (YYYY-MM-DD):", dateTextField), gbc);

        gbc.gridy++;
        mainPanel.add(createInputPanel("Location:", locationTextField), gbc);

        gbc.gridy++;
        mainPanel.add(submitButton, gbc);

        submitButton.addActionListener(this);

        setContentPane(mainPanel);
    }

    private JPanel createInputPanel(String label, JTextField... textFields) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 240, 230));
        panel.setBorder(BorderFactory.createLineBorder(new Color(188, 143, 143), 2));
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        labelComponent.setForeground(new Color(188, 143, 143));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBackground(new Color(255, 240, 230));

        for (JTextField textField : textFields) {
            textField.setBorder(BorderFactory.createRoundedBorder(8, new Color(188, 143, 143)));
            inputPanel.add(textField);
            inputPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add horizontal spacing
        }

        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(inputPanel, BorderLayout.CENTER);

        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String coupleOneFirstName = coupleOneFNTextField.getText();
        String coupleOneLastName = coupleOneLNTextField.getText();
        String coupleTwoFirstName = coupleTwoFNTextField.getText();
        String coupleTwoLastName = coupleTwoLNTextField.getText();
        String weddingDateString = dateTextField.getText();
        String location = locationTextField.getText();

        Couple couple = new Couple(new Person(coupleOneFirstName, coupleOneLastName),
                new Person(coupleTwoFirstName, coupleTwoLastName));

        LocalDate weddingDate = LocalDate.parse(weddingDateString);

        Wedding wedding = new Wedding(couple, weddingDate, location);

        JOptionPane.showMessageDialog(this, "Wedding Details:\n\n"
                + "Couple One: " + wedding.getCouple().getPerson1().getFirstName() + " "
                + wedding.getCouple().getPerson1().getLastName() + "\n"
                + "Couple Two: " + wedding.getCouple().getPerson2().getFirstName() + " "
                + wedding.getCouple().getPerson2().getLastName() + "\n"
                + "Wedding Date: " + wedding.getWeddingDate() + "\n"
                + "Location: " + wedding.getLocation());
    }

    public static void main(String[] args) {
        TestWedding weddingPlanner = new TestWedding();
        weddingPlanner.setVisible(true);
    }
}
