// Import necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// ShoppingCartGUI class that extends JFrame and implements ActionListener
public class ShoppingCartGUI extends JFrame implements ActionListener {

    private ShoppingCart shoppingCart; // Instance variable for the shopping cart
    private JComboBox<Item> itemComboBox; // Combo box for selecting items
    private JComboBox<Integer> removeQuantityComboBox; // Combo box for selecting quantity to remove
    private JTextField quantityTextField; // Text field for entering quantity to add
    private JButton addItemButton; // Button for adding items
    private JButton removeItemButton; // Button for removing items
    private JButton showTotalButton; // Button for showing total price
    private JTextArea receiptTextArea; // Text area for displaying the shopping cart items

    // Constructor for ShoppingCartGUI class
    public ShoppingCartGUI() {
        super("Shopping Cart"); // Set the title of the JFrame
        setSize(600, 300); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation when the frame is closed

        shoppingCart = new ShoppingCart(); // Initialize the shopping cart

        // Populate the list of items
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Tissues", "Kleenex", 3.0));
        itemList.add(new Item("Bulk Tissues", "Generic", 4.0));
        itemList.add(new Item("Fresh Apples", "Organic Farms", 2.5));
        // Add more items as needed...

        // Initialize components
        itemComboBox = new JComboBox<>(itemList.toArray(new Item[0]));
        removeQuantityComboBox = new JComboBox<>(); // Initialize the removeQuantityComboBox
        quantityTextField = new JTextField();

        addItemButton = new JButton("Add Item");
        removeItemButton = new JButton("Remove Item");
        showTotalButton = new JButton("Show Total Price");

        // Initialize the receipt text area with a scroll pane
        receiptTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(receiptTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout()); // Set the layout of the JFrame

        // Create input panel with labels, combo boxes, and text field
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Select Item:"));
        inputPanel.add(itemComboBox);
        inputPanel.add(new JLabel("Quantity to Add:"));
        inputPanel.add(quantityTextField);
        inputPanel.add(new JLabel("Select Quantity to Remove:"));
        inputPanel.add(removeQuantityComboBox);

        // Create button panel with buttons for adding, removing, and showing total price
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(addItemButton);
        buttonPanel.add(removeItemButton);
        buttonPanel.add(showTotalButton);

        // Create right panel to hold button panel and receipt text area
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        addActionListeners(); // Add action listeners to buttons and combo boxes
        populateRemoveQuantityComboBox(); // Populate the removeQuantityComboBox initially

        setVisible(true); // Make the frame visible
    }

    // Add action listeners to buttons and combo boxes
    private void addActionListeners() {
        addItemButton.addActionListener(this);
        removeItemButton.addActionListener(this);
        showTotalButton.addActionListener(this);
        itemComboBox.addActionListener(this);
    }

    // Get the selected item from the itemComboBox
    private Item getSelectedItem() {
        return (Item) itemComboBox.getSelectedItem();
    }

    // ActionListener implementation method
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks and combo box selections
        if (e.getSource() == addItemButton) {
            addItemToCart(); // Add item to the cart
        } else if (e.getSource() == removeItemButton) {
            removeItemFromCart(); // Remove item from the cart
        } else if (e.getSource() == showTotalButton) {
            showTotalPrice(); // Show total price of items in the cart
        } else if (e.getSource() == itemComboBox) {
            populateRemoveQuantityComboBox(); // Populate removeQuantityComboBox based on selected item
        }
    }

    // Add item to the shopping cart
    private void addItemToCart() {
        try {
            Item selectedItem = getSelectedItem(); // Get the selected item
            String quantityText = quantityTextField.getText(); // Get the quantity text from the text field

            // Validate quantity input
            if (quantityText.isEmpty()) {
                updateCartMessage("Quantity cannot be empty.");
                return;
            }

            int quantity = Integer.parseInt(quantityText); // Convert quantity text to integer
            shoppingCart.addItemOrder(new ItemOrder(selectedItem, quantity)); // Add item to the cart
            quantityTextField.setText(""); // Clear quantity text field
            updateCartMessage("Item added to cart: " + selectedItem.toString() + " x" + quantity); // Update message
            updateReceipt(); // Update the receipt display
            populateRemoveQuantityComboBox(); // Update removeQuantityComboBox
        } catch (NumberFormatException e) {
            updateCartMessage("Invalid quantity. Please enter a valid number.");
        }
    }

    // Remove item from the shopping cart
    private void removeItemFromCart() {
        Item selectedItem = getSelectedItem(); // Get the selected item
        if (selectedItem == null) {
            updateCartMessage("Please select an item to remove.");
            return;
        }

        try {
            Integer selectedQuantity = (Integer) removeQuantityComboBox.getSelectedItem(); // Get selected quantity
            if (selectedQuantity == null || selectedQuantity <= 0) {
                updateCartMessage("Please select a valid quantity to remove.");
                return;
            }

            // Iterate through item orders to find and update/remove the item
            boolean found = false;
            for (ItemOrder order : shoppingCart.getItemOrders()) {
                if (order.getItem().equals(selectedItem)) {
                    if (order.getQuantity() <= selectedQuantity) {
                        shoppingCart.removeItemOrder(order);
                        updateCartMessage("Item removed from cart: " + selectedItem.toString());
                    } else {
                        order.setQuantity(order.getQuantity() - selectedQuantity);
                        updateCartMessage("Quantity updated for: " + selectedItem.toString());
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                updateCartMessage("Selected item not found in the cart.");
            }

            updateReceipt(); // Update the receipt display
            populateRemoveQuantityComboBox(); // Update removeQuantityComboBox
        } catch (NumberFormatException e) {
            updateCartMessage("Invalid quantity selected.");
        }
    }

    // Calculate and display the total price of items in the shopping cart
    private void showTotalPrice() {
        double total = shoppingCart.calculateTotalPrice(); // Calculate total price
        updateCartMessage("Total Price: $" + total); // Update message with total price
        updateReceipt(); // Update the receipt display
    }

    // Display a message in a dialog box
    private void updateCartMessage(String message) {
        JOptionPane.showMessageDialog(this, message); // Show message in a dialog box
    }

    // Update the content of the receiptTextArea
    private void updateReceipt() {
        StringBuilder receiptBuilder = new StringBuilder("Shopping Cart:\n\n");

        // Append item orders to the receipt text
        for (ItemOrder itemOrder : shoppingCart.getItemOrders()) {
            receiptBuilder.append(itemOrder.toString()).append("\n");
        }

        // Update the receipt text area in the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            receiptTextArea.setText("");  // Clear the text area
            receiptTextArea.append(receiptBuilder.toString());  // Append the new content
        });
    }

    // Populate the removeQuantityComboBox with quantities for the selected item
    private void populateRemoveQuantityComboBox() {
        Item selectedItem = getSelectedItem(); // Get the selected item
        if (selectedItem != null) {
            removeQuantityComboBox.removeAllItems(); // Clear the existing items
            // Add quantities to the combo box based on the item's quantity in the cart
            for (int i = 1; i <= shoppingCart.getQuantityForItem(selectedItem); i++) {
                removeQuantityComboBox.addItem(i);
            }
        }
    }

    // Main method to create and display the ShoppingCartGUI
    public static void main(String[] args) {
        ShoppingCartGUI example = new ShoppingCartGUI(); // Create an instance of ShoppingCartGUI
        example.setVisible(true); // Make the frame visible
    }
}