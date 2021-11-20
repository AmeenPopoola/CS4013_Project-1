/**Public Class for Reservation */
public class Reservation {
    private String description;
    private ReservationDate day;
    private ReservationTime from;
    private ReservationTime to;

    /**Reservation Constructor method */
    public Reservation(String description, ReservationDate day, ReservationTime from, ReservationTime to){
        this.description = description;
        this.day = day;
        this.from = from;
        this.to = to;

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReservationDate getDay() {
        return this.day;
    }

    public void setDay(ReservationDate day) {
        this.day = day;
    }

    public ReservationTime getFrom() {
        return this.from;
    }

    public void setFrom(ReservationTime from) {
        this.from = from;
    }

    public ReservationTime getTo() {
        return this.to;
    }

    public void setTo(ReservationTime to) {
        this.to = to;
    }

    
}
