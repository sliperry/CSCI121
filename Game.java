import javax.swing.*;
import java.io.*;

public class Game {
    Questions root; // Declare a variable 'root' of type Questions to represent the root node of the binary tree

    public Game() {
        this.root = null; // Initialize the 'root' variable to null in the constructor of the Game class
    }

    public void constructTree() {
        // Initial tree construction with a root question node
        root = new Questions("Is it a vertebrate?");

        // Constructing branches of the binary tree with specific questions
        root.yesBranch = new Questions("Is it a mammal?");
        root.yesBranch.yesBranch = new Questions("Does it have fur?");
        root.yesBranch.yesBranch.yesBranch = new Questions("Is it a Carnivore?");
        root.yesBranch.yesBranch.yesBranch.yesBranch = new Questions("Lion");
        root.yesBranch.yesBranch.yesBranch.noBranch = new Questions("Cow");

        root.yesBranch.yesBranch.noBranch = new Questions("Is it a aquatic?");
        root.yesBranch.yesBranch.noBranch.yesBranch = new Questions("Whale");
        root.yesBranch.yesBranch.noBranch.noBranch = new Questions("Elephants");

        root.yesBranch.noBranch = new Questions("Is it a bird?");
        root.yesBranch.noBranch.yesBranch = new Questions("Is it a flightless bird?");
        root.yesBranch.noBranch.yesBranch.yesBranch = new Questions("Ostrich");
        root.yesBranch.noBranch.yesBranch.noBranch = new Questions("Sparrow Hawk");

        root.yesBranch.noBranch.noBranch = new Questions("Is it a amphibian?");
        root.yesBranch.noBranch.noBranch.yesBranch = new Questions("red eye frog");
        root.yesBranch.noBranch.noBranch.noBranch = new Questions("Boa Constructor");

        root.noBranch = new Questions("Is it a insects?");

        root.noBranch.yesBranch = new Questions("Does it typically fly?");
        root.noBranch.yesBranch.yesBranch = new Questions("Honey bee");
        root.noBranch.yesBranch.noBranch = new Questions("Spider");

        root.noBranch.noBranch = new Questions("Is it a Spider?");
        root.noBranch.noBranch.yesBranch = new Questions("Daddy-long legs");
        root.noBranch.noBranch.noBranch = new Questions("Garden Snail");
    }


    public void saveTree(Questions node, String filename) {
        try {
            // Create FileOutputStream and ObjectOutputStream to write the tree node to a file
            try (FileOutputStream fileOut = new FileOutputStream(filename);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(node); // Write the tree node to the ObjectOutputStream
            }
        } catch (IOException e) {
            // Handle IO exceptions during file writing
            JOptionPane.showMessageDialog(null, "Error saving tree: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Questions loadTree(String filename) {
        try {
            // Create FileInputStream and ObjectInputStream to read the tree node from a file
            try (FileInputStream fileIn = new FileInputStream(filename);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                return (Questions) objectIn.readObject(); // Read and return the tree node from the ObjectInputStream
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle IO or class not found exceptions during file reading
            JOptionPane.showMessageDialog(null, "Error loading tree: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Return null if an exception occurs
        }
    }

    public String playGame(Questions root, TwentyQuestionsGUI gui) {
        Questions current = root; // Set the current node to the root node of the tree
        while (true) {
            if (current.yesBranch != null) {
                // Existing question node
                String answer = JOptionPane.showInputDialog(current.data); // Display the current question to the user
                if (answer == null) {  // User canceled the input
                    return "cancel"; // Return "cancel" if the user cancels the game
                }
                answer = answer.toLowerCase(); // Convert the user's input to lowercase for case-insensitive comparison
                if (answer.equals("yes")) {
                    current = current.yesBranch; // Move to the yes branch if the user answers "yes"
                } else if (answer.equals("no")) {
                    current = current.noBranch; // Move to the no branch if the user answers "no"
                } else {
                    JOptionPane.showMessageDialog(gui, "Invalid input. Please enter 'yes' or 'no'."); // Display an error message for invalid input
                }
            } else {
                // New question node
                String guess = JOptionPane.showInputDialog("Is it a " + current.data + "? (Please enter 'yes' or 'no')"); // Ask the user for a guess based on the current node
                if (guess == null) {  // User canceled the input
                    return "cancel"; // Return "cancel" if the user cancels the game
                }
                guess = guess.toLowerCase(); // Convert the user's input to lowercase for case-insensitive comparison
                // Exit the game loop after adding new information to the tree
                if (guess.equals("yes")) {
                    JOptionPane.showMessageDialog(gui, "I win!"); // Display a win message if the user's guess is correct
                } else {
                    // Prompt the user for new information to add to the tree
                    String newObject = JOptionPane.showInputDialog("What were you thinking of?");
                    if (newObject == null) {  // User canceled the input
                        return "cancel"; // Return "cancel" if the user cancels the game
                    }
                    String newQuestion = JOptionPane.showInputDialog("Please provide a question that is true for " +
                            newObject + " and false for " + current.data + ":"); // Ask the user for a new question
                    if (newQuestion == null) {  // User canceled the input
                        return "cancel"; // Return "cancel" if the user cancels the game
                    }
                    // Connect the new question node to its parent with the correct answer
                    current.yesBranch = new Questions(newObject);
                    current.yesBranch.setParent(current);
                    current.noBranch = new Questions(current.data);
                    current.noBranch.setParent(current);
                    current.data = newQuestion; // Update the current node's data with the new question
                    saveTree(root, "tree_state.ser");  // Save the updated tree structure to a file
                    JOptionPane.showMessageDialog(null, "Thank you! I'll remember that for next time."); // Display a message acknowledging the new information
                }
                break; // Exit the game loop
            }
        }
        return "Continue"; // Game continues unless canceled
    }
}