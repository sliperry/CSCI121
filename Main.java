import java.math.BigDecimal;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Greetings, weary traveler, and welcome to the Enchanted Realm of Currency Alchemy!");
        System.out.println("I am Merlinus the Magnificent, master of the mystical arts, and I bid thee welcome.");

        System.out.print("Pray, reveal the mundane amount you wish to transmute into magical coins: $");
        double amount = sc.nextDouble();

        System.out.println("\nAh, the essence of " + amount + " gold coins! An intriguing choice indeed.");
        System.out.println("Now, let us begin the alchemical process...");

        BigDecimal money = BigDecimal.valueOf(amount);

        BigDecimal quarterVal = BigDecimal.valueOf(0.25);
        int quarters = money.divide(quarterVal, 0, BigDecimal.ROUND_DOWN).intValue();
        money = money.remainder(quarterVal);

        BigDecimal dimeVal = BigDecimal.valueOf(0.1);
        int dimes = money.divide(dimeVal, 0, BigDecimal.ROUND_DOWN).intValue();
        money = money.remainder(dimeVal);

        BigDecimal nickelVal = BigDecimal.valueOf(0.05);
        int nickels = money.divide(nickelVal, 0, BigDecimal.ROUND_DOWN).intValue();
        money = money.remainder(nickelVal);

        BigDecimal pennyMultVal = BigDecimal.valueOf(100);
        int pennies = money.multiply(pennyMultVal).intValue();

        System.out.println("\nThe cauldron bubbles and the stars align as we witness the transmutation of $" + amount + ":");

        if (quarters != 0) {
            System.out.println(quarters + " Quarters");
        }

        if (dimes != 0) {
            System.out.println(dimes + " Dimes");
        }

        if (nickels != 0) {
            System.out.println(nickels + " Nickels");
        }

        if (pennies != 0) {
            System.out.println(pennies + " Pennies");
        }

        System.out.println("\nBehold the enchanted coins, imbued with the essence of the cosmos!");
        System.out.println("May these magical coins bring fortune and wonder to your journey, brave adventurer.");
    }

    private static void performTransmutation(int quantity, String denomination) {
        if (quantity != 0) {
            System.out.println("\uD83D\uDD2E " + quantity + " " + denomination); // Utilizing mystical emojis for added enchantment!
        }
    }
}
