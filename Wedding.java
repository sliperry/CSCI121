import java.time.LocalDate;

public class Wedding {

    private Couple couple;
    private LocalDate weddingDate;
    private String location;

    // Constructor requiring a Couple, a LocalDate, and a location String
    public Wedding(Couple couple, LocalDate weddingDate, String location) {
        this.couple = couple;
        this.weddingDate = weddingDate;
        this.location = location;
    }

    // Get method for the Couple
    public Couple getCouple() {

        return couple;
    }

    // Get method for the wedding date
    public LocalDate getWeddingDate() {

        return weddingDate;
    }

    // Get method for the location
    public String getLocation() {

        return location;
    }

}
