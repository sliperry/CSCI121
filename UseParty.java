import java.util.Scanner;

public class UseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a Party object
        Party aParty = new Party();

        // Prompt user for the number of guests
        System.out.print("✨ Welcome to the Grand Masquerade Soiree! 🎭\nEnter the number of esteemed guests for the party >> ");
        int guests = scanner.nextInt();

        // Set the number of guests in the Party object
        aParty.setGuests(guests);

        // Display the number of guests
        System.out.println("\n🌟 Splendid! Your soirée is set with " + aParty.getGuests() + " distinguished attendees!");

        // Display the sophisticated invitation
        System.out.println("🎭✨ Join us for an enchanting evening - " + aParty.getGuests() + " graceful souls will waltz beneath the stars!");
        aParty.displayInvitation();
    }
}