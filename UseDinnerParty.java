import java.util.Scanner;

public class UseDinnerParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a DinnerParty object
        DinnerParty aDinnerParty = new DinnerParty();

        // Prompt user for the number of guests for the dinner party
        System.out.print("🍷🍽✨ Welcome to the Gastronomic Gala! 🍲🎩\nEnter the number of esteemed diners for the gala >> ");
        int guests = scanner.nextInt();

        // Set the number of guests in the DinnerParty object
        aDinnerParty.setGuests(guests);

        // Prompt user for the dinner choice
        System.out.print("🍽🌟 Choose a culinary masterpiece - type 1 for Truffle-infused Delicacies or 2 for Exquisite Vegetarian Symphony >> ");
        int choice = scanner.nextInt();

        // Set the dinner choice in the DinnerParty object
        aDinnerParty.setDinnerChoice(choice);

        // Display the number of guests and dinner choice for the DinnerParty
        System.out.println("\n🌠✨ Exquisite! You're hosting a gala with " + aDinnerParty.getGuests() + " connoisseurs of fine dining.");
        System.out.println("🍽️ Indulge in the culinary artistry - Menu option " + aDinnerParty.getDinnerChoice() + " will be served with grace!");

        // Display the sophisticated invitation for the DinnerParty
        System.out.println("✨ Join us for a gastronomic journey with delightful conversations and culinary masterpieces!");
        aDinnerParty.displayInvitation();
    }
}