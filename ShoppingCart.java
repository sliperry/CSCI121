import java.util.ArrayList;

// ShoppingCart class to manage item orders in a shopping cart
public class ShoppingCart {
    private ArrayList<ItemOrder> itemOrders; // List to store item orders

    // Constructor to initialize the shopping cart with an empty list of item orders
    public ShoppingCart() {
        this.itemOrders = new ArrayList<>();
    }

    // Method to add an item order to the shopping cart
    public void addItemOrder(ItemOrder itemOrder) {
        itemOrders.add(itemOrder); // Add the item order to the list
    }

    // Method to remove an item order from the shopping cart
    public void removeItemOrder(ItemOrder itemOrder) {
        itemOrders.remove(itemOrder); // Remove the item order from the list
    }

    // Method to calculate the total price of all item orders in the shopping cart
    public double calculateTotalPrice() {
        double totalPrice = 0;
        // Iterate through each item order and calculate its total price, then sum them up
        for (ItemOrder itemOrder : itemOrders) {
            totalPrice += itemOrder.calculateTotal();
        }
        return totalPrice; // Return the total price
    }

    // Getter method to retrieve the list of item orders in the shopping cart
    public ArrayList<ItemOrder> getItemOrders() {
        return itemOrders; // Return the list of item orders
    }

    // Method to get the total quantity of a specific item in the shopping cart
    public int getQuantityForItem(Item item) {
        int totalQuantity = 0;
        // Iterate through each item order and check if it matches the specified item
        for (ItemOrder itemOrder : itemOrders) {
            if (itemOrder.getItem().equals(item)) {
                totalQuantity += itemOrder.getQuantity(); // Add the quantity to the total
            }
        }
        return totalQuantity; // Return the total quantity for the item
    }
}