// Represents a shopper's desire to purchase a given item in a given quantity
class ItemOrder {
    private Item item; // The item being ordered
    private int quantity; // The quantity of the item being ordered

    // Constructor to initialize an item order with the specified item and quantity
    public ItemOrder(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // Getter method to retrieve the item in the item order
    public Item getItem() {
        return item;
    }

    // Getter method to retrieve the brand of the item in the item order
    public String getBrand() {
        return item.getBrand();
    }

    // Getter method to retrieve the name of the item in the item order
    public String getItemName() {
        return item.getName();
    }

    // Getter method to retrieve the quantity of the item in the item order
    public int getQuantity() {
        return quantity;
    }

    // Setter method to set the quantity of the item in the item order
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculates the total cost of the item order (price * quantity)
    public double calculateTotal() {
        return item.getPrice() * quantity;
    }

    // Override toString method to provide a string representation of the item order
    @Override
    public String toString() {
        return item.getBrand() + " " + item.getName() + " x" + quantity + " - $" + calculateTotal();
    }
}