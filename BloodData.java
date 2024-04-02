public class BloodData {

    private BloodGroup BGroup;
    private char RHType;

    // Default constructor
    public BloodData(BloodGroup BGroup, char RHType) {
        this.BGroup = BGroup;
        this.RHType = RHType;
    }

    // Overloaded constructor with values for each field
    public BloodData() {
        this.BGroup = BloodGroup.O;
        this.RHType = '+';
    }

    // Setter for blood type
    public void setBloodGroup(BloodGroup BGroup) {
        this.BGroup = BGroup;
    }

    // Setter for rh type
    public void setRHType(char RHType) {
        this.RHType = RHType;
    }

    // Getter for blood type
    public BloodGroup getBloodGroup() {
        return BGroup;
    }

    // Getter for rh type
    public char getRHType() {
        return RHType;
    }
}
