import java.math.BigDecimal;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the amount of cash in dollars ($):");
        double amount = sc.nextDouble();

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

        System.out.println("Change breakdown for $" + amount + ":");

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
    }
}
