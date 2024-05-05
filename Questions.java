import java.io.Serializable;

public class Questions implements Serializable {
    String data; // Stores the data associated with this node (question or answer)

    Questions parent; // Reference to the parent node
    Questions yesBranch; // Reference to the node representing a "yes" answer
    Questions noBranch; // Reference to the node representing a "no" answer

    // Constructor for answer nodes
    public Questions(String data) {
        this.data = data; // Initialize the data for this node with the provided value
        this.parent = null; // Set the parent node initially to null
        this.yesBranch = null; // Set the "yes" branch initially to null
        this.noBranch = null; // Set the "no" branch initially to null
    }

    // Setter method to set the parent node of this node
    public void setParent(Questions parent) {
        this.parent = parent; // Set the parent node to the provided parent
    }
}
