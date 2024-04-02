import java.util.Scanner;

public class TestBloodData  {

    public static void main(String[] args) {

        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Create two BloodData objects
        BloodData magicianGuess = new BloodData(); // Default constructor

        System.out.println("Magician: Welcome, seeker of mysteries, to the Chamber of Magical Blood!");

        System.out.println("Magician: The ethereal whispers of the cosmos reveal to me your essence...");

        System.out.println("Magician: Behold! I sense your blood type and Rh factor...");

        System.out.println("Magician's Guess: " + magicianGuess.getBloodGroup() + " " + magicianGuess.getRHType());

        // Ask the user if the magician's guess is correct
        try {
            System.out.print("Magician: Is my divination accurate? (Y/N): ");
            String response = scanner.nextLine().toUpperCase(); // Get user's response in uppercase

            // Handle the user's response with humor
            if (response.equals("N")) {
                System.out.println("Magician: Oh dear, it seems I need to brush up on my mystical skills. Or perhaps it's just a cosmic prank!");
                BloodData corectAnswer = new BloodData(BloodGroup.O, '+');

                // Ask the user for their blood type and Rh factor
                System.out.print("Magician: Tell me, what is your blood type? (O, A, B, AB): ");
                corectAnswer.setBloodGroup(BloodGroup.valueOf(scanner.nextLine().toUpperCase())); // Set blood type

                System.out.print("Magician: And what about your Rh factor? (+ or -): ");
                corectAnswer.setRHType(scanner.nextLine().charAt(0)); // Set Rh factor

                System.out.println("Magician: Ah, the mystical energies resonate with your blood!");


                // Display user's blood data
                System.out.println("User's Blood Data: " + corectAnswer.getBloodGroup() + " " + corectAnswer.getRHType());
            } else if (response.equals("Y")) {

                System.out.println("Magician: Haha! My magical prowess knows no bounds! Your blood is truly enchanting!");

            } else {

                System.out.println("Magician: Hm, it seems my magic did not foresee this response. Let's try again with clearer incantations.");

            }

            System.out.println("Magician: Until our paths cross again, may the magic of blood illuminate your journey!");
        } catch (IllegalArgumentException e) {
            System.out.println("Magician: Hm, it seems the ancient tomes did not predict this input. Let's try again with clearer incantations.");
        }

        // Close the scanner
        scanner.close();

    }
}