// Step 1
public class Party {
    // Step 1
    private int numberOfGuests;

    // Step 2
    public void setGuests(int num) {
        numberOfGuests = num;
    }

    public int getGuests() {
        return numberOfGuests;
    }

    // Step 3
    public void displayInvitation() {
        System.out.println("Please come to my party!");
    }
}