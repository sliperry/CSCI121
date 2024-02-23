import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double [] nums = new double[20];
        int count = 0;

        System.out.println("Enter up to 20 numbers of double value:");

        for(int i = 0; i < 20; i++) {
            System.out.println("number count "+(i+1));
            double temp = sc.nextDouble();
            count = i;

            if (temp == 9999) {
                break;
            } else {
                nums[i] = temp;
            }
        }

        System.out.println("YEYYYY!!!!!!!!!!! you finished here is the array "+Arrays.toString(Arrays.copyOfRange(nums,0,count)));

    }
}