import java.util.Scanner;

public class TestPatient {

    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Declare three Patient objects
        Patient patient1 = new Patient(); // Default values for BloodData
        Patient patient2 = null; // Placeholder for patient2
        Patient patient3 = null; // Placeholder for patient3

        try {
            // Nurse introduces the scenario to the doctor in a professional manner
            System.out.println("Nurse: Good day, Doctor. Today, we'll review the blood group records of our patients.");
            System.out.println("Nurse: Let's ensure the accuracy of our medical records for optimal patient care.");

            // Nurse guides the doctor to review the blood group record for patient 2
            System.out.println("\nNurse: Let's start with patient 2's blood group record:");
            System.out.print("Nurse: Please enter ID number for patient 2: ");
            int id2 = Integer.parseInt(scanner.nextLine());

            System.out.print("Nurse: Please enter age for patient 2: ");
            int age2 = Integer.parseInt(scanner.nextLine());

            System.out.print("Nurse: Please enter blood type for patient 2 (O, A, B, AB): ");
            BloodGroup bloodGroup2 = BloodGroup.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Nurse: Please enter Rh factor for patient 2 (+ or -): ");
            char rhType2 = scanner.nextLine().charAt(0);

            // Create patient2 using the provided values
            patient2 = new Patient(id2, age2, bloodGroup2, rhType2);

            // Nurse guides the doctor to review the blood group record for patient 3
            System.out.println("\nNurse: Now, let's review patient 3's blood group record:");
            System.out.print("Nurse: Please enter ID number for patient 3: ");
            int id3 = Integer.parseInt(scanner.nextLine());

            System.out.print("Nurse: Please enter age for patient 3: ");
            int age3 = Integer.parseInt(scanner.nextLine());

            // Create patient3 using default BloodData values
            patient3 = new Patient(id3, age3, BloodGroup.O, '+');
        } catch (NumberFormatException e) {
            System.out.println("Nurse: Invalid input for ID or age. Please enter numeric values.");
            return; // Exit the program if input is invalid
        } catch (IllegalArgumentException e) {
            System.out.println("Nurse: Invalid input for blood type or Rh factor. Please enter valid values.");
            return; // Exit the program if input is invalid
        } finally {
            // Close the scanner
            scanner.close();
        }

        // Display details of all three patients
        System.out.println("\nNurse: Let's review the blood group records for our patients:");
        System.out.println("\nPatient 1 Details:");
        displayPatientDetails(patient1);

        System.out.println("\nPatient 2 Details:");
        displayPatientDetails(patient2);

        System.out.println("\nPatient 3 Details:");
        displayPatientDetails(patient3);
    }

    // Method to display Patient details
    private static void displayPatientDetails(Patient patient) {
        if (patient != null) {
            System.out.println("ID: " + patient.getId());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Blood Group: " + patient.getBloodGroup());
            System.out.println("RH Type: " + patient.getRHType());
        } else {
            System.out.println("Nurse: Patient data is missing or invalid.");
        }
    }

}
