// Represents a grocery item with a name, brand, and price
class Item {
    private String name; // The name of the item
    private String brand; // The brand of the item
    private double price; // The price of the item

    // Constructor to initialize an item with the specified name, brand, and price
    public Item(String name, String brand, double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    // Getter method to retrieve the name of the item
    public String getName() {
        return name;
    }

    // Getter method to retrieve the brand of the item
    public String getBrand() {
        return brand;
    }

    // Getter method to retrieve the price of the item
    public double getPrice() {
        return price;
    }

    // Override toString method to provide a string representation of the item
    @Override
    public String toString() {
        return brand + " " + name + " - $" + price;
    }
}