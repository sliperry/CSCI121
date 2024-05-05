import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwentyQuestionsGUI extends JFrame implements ActionListener {
    private final JButton startButton;
    private final JButton resetButton;
    private final JTextArea textArea;
    private final Game game;

    public TwentyQuestionsGUI() {
        // Set up the JFrame
        super("20 Questions Game"); // Set the title of the JFrame
        setSize(400, 300); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation

        // Initialize the game and construct the tree
        game = new Game();
        game.constructTree();

        // Create the main panel and set its layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a text area for displaying game messages
        textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area read-only
        JScrollPane scrollPane = new JScrollPane(textArea); // Add scroll functionality to the text area
        panel.add(scrollPane, BorderLayout.CENTER); // Add the text area to the center of the panel

        // Create a panel for buttons (Start Game and Reset Game)
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start Game"); // Create a button for starting the game
        resetButton = new JButton("Reset Game"); // Create a button for resetting the game

        startButton.addActionListener(this); // Add an ActionListener to the Start Game button
        resetButton.addActionListener(this); // Add an ActionListener to the Reset Game button

        buttonPanel.add(startButton); // Add the Start Game button to the button panel
        buttonPanel.add(resetButton); // Add the Reset Game button to the button panel

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom of the main panel

        add(panel); // Add the main panel to the JFrame
        setVisible(true); // Set the JFrame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) { // If the Start Game button is clicked
            textArea.setText(""); // Clear the text area
            // Load the tree state, start the game, and get the result
            String result = game.playGame(game.loadTree("tree_state.ser"), this);
            if (result.equals("cancel")) { // If the game is canceled
                textArea.append("Game canceled.\n"); // Display a message in the text area
            }
        } else if (e.getSource() == resetButton) { // If the Reset Game button is clicked
            game.saveTree(game.root, "tree_state.ser"); // Save the current tree state
            textArea.append("Game saved.\n"); // Display a message in the text area
        }
    }
}
