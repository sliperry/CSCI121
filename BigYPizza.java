import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BigYPizza extends JFrame implements ItemListener, ActionListener {
    // Constants for the frame dimensions
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 300;

    // GUI components
    private JComboBox<String> pizzaSize;
    private JCheckBox[] toppings;
    private JLabel priceTxt;
    private JLabel priceCash;
    private int toppingsCount = 0;
    private JButton placeOrderButton;

    // Constructor for the BigY Pizza application
    public BigYPizza() {
        // Set frame properties
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Heading label
        JLabel heading = new JLabel("Welcome to BIG Y'S Pizza Delight!!!");
        heading.setFont(new Font("Arial", Font.BOLD, 32));

        // Pizza size dropdown
        pizzaSize = new JComboBox<>();
        pizzaSize.addItem("Small");
        pizzaSize.addItem("Medium");
        pizzaSize.addItem("Large");
        pizzaSize.addItem("Super");
        pizzaSize.addItemListener(this);

        // Toppings checkboxes
        toppings = new JCheckBox[]{
                new JCheckBox("Pepperoni"),
                new JCheckBox("Bacon"),
                new JCheckBox("Sausages"),
                new JCheckBox("Onions"),
                new JCheckBox("Mushrooms"),
                new JCheckBox("Extra Cheese"),
                new JCheckBox("Peppers"),
                new JCheckBox("Black Olives")
        };

        // Price labels
        priceTxt = new JLabel("Due Amount ($):");
        priceCash = new JLabel("$##.##");

        // Button for placing the order
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(this);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Add components to the frame
        add(heading, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(createPizzaSizePanel());
        centerPanel.add(createToppingsPanel());
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(priceTxt);
        bottomPanel.add(priceCash);
        bottomPanel.add(placeOrderButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Create panel for selecting pizza size
    private JPanel createPizzaSizePanel() {
        JPanel pizzaSizePanel = new JPanel(new FlowLayout());
        pizzaSizePanel.add(new JLabel("Select Pizza Size:"));
        pizzaSizePanel.add(pizzaSize);
        return pizzaSizePanel;
    }

    // Create panel for selecting toppings
    private JPanel createToppingsPanel() {
        JPanel toppingsPanel = new JPanel(new GridLayout(0, 2));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));

        // Create category panels for meats and vegetables
        JPanel meatsPanel = createToppingsCategoryPanel("Meats", 0, 2);
        JPanel vegetablesPanel = createToppingsCategoryPanel("Vegetables", 3, 7);

        toppingsPanel.add(meatsPanel);
        toppingsPanel.add(vegetablesPanel);

        return toppingsPanel;
    }

    // Create category panel for specific range of toppings
    private JPanel createToppingsCategoryPanel(String categoryName, int startIdx, int endIdx) {
        JPanel categoryPanel = new JPanel(new GridLayout(0, 1));
        categoryPanel.setBorder(BorderFactory.createTitledBorder(categoryName));

        // Add checkboxes for toppings in the specified range
        for (int i = startIdx; i <= endIdx; i++) {
            JCheckBox topping = toppings[i];
            categoryPanel.add(topping);
            topping.addItemListener(this);
        }

        return categoryPanel;
    }

    // Handle item state changes (selection/deselection)
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == pizzaSize || e.getSource() instanceof JCheckBox) {
            updatePrice();
        }
    }

    // Update the displayed price based on user selections
    private void updatePrice() {
        String selectedSize = (String) pizzaSize.getSelectedItem();
        double basePrice = getBasePrice(selectedSize);
        double toppingsCost = calculateToppingsCost();
        double totalPrice = basePrice + toppingsCost;
        priceCash.setText(String.format("$%.2f", totalPrice));
    }

    // Get the base price of the selected pizza size
    private double getBasePrice(String selectedSize) {
        switch (selectedSize) {
            case "Small":
                return 5.0;
            case "Medium":
                return 10.0;
            case "Large":
                return 15.0;
            case "Super":
                return 20.0;
            default:
                return 0.0;
        }
    }

    // Calculate the cost of selected toppings based on specified rules
    private double calculateToppingsCost() {
        toppingsCount = 0;
        double toppingsCost = 0.0;

        for (JCheckBox topping : toppings) {
            if (topping.isSelected()) {
                toppingsCount++;
            }
            topping.setEnabled(true);
        }

        // Apply additional cost for two or three toppings
        if (toppingsCount == 1) {
            toppingsCost = 0.5;
        } else if (toppingsCount == 2) {
            toppingsCost = 1.0;
        } else if (toppingsCount == 3) {
            toppingsCost = 1.25;
            for (JCheckBox topping : toppings) {
                if (!topping.isSelected()) {
                    topping.setEnabled(false);
                }
            }
        }

        return toppingsCost;
    }

    // Handle button click event for placing the order
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == placeOrderButton) {
            placeOrder();
        }
    }

    // Display a dialogue with order details
    private void placeOrder() {
        String selectedSize = (String) pizzaSize.getSelectedItem();
        StringBuilder selectedToppings = new StringBuilder();

        // Build a string with selected toppings
        for (JCheckBox topping : toppings) {
            if (topping.isSelected()) {
                selectedToppings.append(topping.getText()).append(", ");
            }
        }

        // Create order details string
        String orderDetails = String.format("Order Details:\nSize: %s\nToppings: %s", selectedSize,
                selectedToppings.toString().isEmpty() ? "None" : selectedToppings.substring(0, selectedToppings.length() - 2));

        // Display order confirmation dialogue
        JOptionPane.showMessageDialog(this, "Your order has been placed!\n\n" + orderDetails,
                "Order Placed", JOptionPane.INFORMATION_MESSAGE);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        BigYPizza example = new BigYPizza();
        example.setVisible(true);
    }
}
