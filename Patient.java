public class Patient extends BloodData{
    private int id;
    private int age;

    // Default constructor
    public Patient() {
        super(); // Call the default constructor of BloodData
        this.id = 0;
        this.age = 0;
    }

    // Overloaded constructor with values for each field
    public Patient(int id, int age, BloodGroup bloodGroup, char RHType) {
        super(bloodGroup, RHType); // Call the overloaded constructor of BloodData
        this.id = id;
        this.age = age;
    }

    // Setter for ID number
    public void setId(int id) {
        this.id = id;
    }

    // Setter for age
    public void setAge(int age) {
         this.age = age;
    }

    // Getter for ID number
    public int getId() {
        return id;
    }

    // Getter for age
    public int getAge() {
        return age;
    }
}
