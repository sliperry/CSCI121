public class Couple {

    private Person bride;
    private Person groom;

    // Constructor requiring two Person objects
    public Couple(Person bride, Person groom) {
        this.bride = bride;
        this.groom = groom;
    }

    // Get method for the bride
    public Person getPerson1() {
        return bride;
    }

    // Get method for the groom
    public Person getPerson2() {
        return groom;
    }
}
